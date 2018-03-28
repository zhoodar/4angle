package kg.jedi.fourangle.web.security.config

import com.fasterxml.jackson.databind.ObjectMapper
import kg.jedi.fourangle.common.Endpoints
import kg.jedi.fourangle.web.security.RestAuthenticationEntryPoint
import kg.jedi.fourangle.web.security.auth.ajax.AjaxAuthenticationProvider
import kg.jedi.fourangle.web.security.auth.ajax.AjaxLoginProcessingFilter
import kg.jedi.fourangle.web.security.auth.jwt.JwtAuthenticationProvider
import kg.jedi.fourangle.web.security.auth.jwt.JwtTokenAuthenticationProcessingFilter
import kg.jedi.fourangle.web.security.auth.jwt.SkipPathRequestMatcher
import kg.jedi.fourangle.web.security.auth.jwt.extractor.TokenExtractor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.access.channel.ChannelProcessingFilter
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var authenticationEntryPoint: RestAuthenticationEntryPoint
    @Autowired
    lateinit var successHandler: AuthenticationSuccessHandler

    @Qualifier("jwt")
    @Autowired
    lateinit var jwtFailureHandler: AuthenticationFailureHandler

    @Qualifier("ajax")
    @Autowired
    lateinit var ajaxFailureHandler: AuthenticationFailureHandler

    @Autowired
    lateinit var ajaxAuthenticationProvider: AjaxAuthenticationProvider
    @Autowired
    lateinit var jwtAuthenticationProvider: JwtAuthenticationProvider
    @Autowired
    lateinit var tokenExtractor: TokenExtractor
    @Autowired
    lateinit var authenticationManager: AuthenticationManager
    @Autowired
    lateinit var objectMapper: ObjectMapper
    @Autowired
    lateinit var crossOriginFilter: CrossOriginFilter


    companion object {
        val FORM_BASED_LOGIN_ENTRY_POINT = "$Endpoints.API_V1/login"
        val TOKEN_BASED_AUTH_ENTRY_POINT = "${Endpoints.SECURE_API_V1}/**"
        val SWAGGER_ENDPOINTS = arrayOf("/v2/api-docs", "/configuration/ui/**", "/swagger-resources/**",
                "/configuration/security/**", "/swagger-ui.html", "/webjars/**")
    }

    @Throws(Exception::class)
    protected fun buildAjaxLoginProcessingFilter(): AjaxLoginProcessingFilter {
        val filter = AjaxLoginProcessingFilter(FORM_BASED_LOGIN_ENTRY_POINT, successHandler, ajaxFailureHandler, objectMapper)
        filter.setAuthenticationManager(this.authenticationManager)
        return filter
    }

    @Throws(Exception::class)
    protected fun buildJwtTokenAuthenticationProcessingFilter(): JwtTokenAuthenticationProcessingFilter {
        val pathsToSkip = listOf(FORM_BASED_LOGIN_ENTRY_POINT)
        val matcher = SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENTRY_POINT)

        val filter = JwtTokenAuthenticationProcessingFilter(this.jwtFailureHandler, this.tokenExtractor, matcher)
        filter.setAuthenticationManager(this.authenticationManager)
        return filter
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(ajaxAuthenticationProvider)
        auth.authenticationProvider(jwtAuthenticationProvider)
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.addFilterBefore(crossOriginFilter, ChannelProcessingFilter::class.java).cors().and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)

                .and()
                .authorizeRequests()
                .antMatchers(FORM_BASED_LOGIN_ENTRY_POINT).permitAll()

                .and()
                .authorizeRequests()
                .antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated() // Protected API End-points

                .and()
                .addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter::class.java)
                .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(*SWAGGER_ENDPOINTS)
    }
}
