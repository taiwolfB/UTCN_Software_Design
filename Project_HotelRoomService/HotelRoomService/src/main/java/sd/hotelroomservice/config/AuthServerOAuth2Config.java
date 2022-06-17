//package sd.hotelroomservice.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//
//@Configuration
//@EnableAuthorizationServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Import(SecurityConfig.class)
//public class AuthServerOAuth2Config extends AuthorizationServerConfigurerAdapter {
//
//    @Autowired
//    @Qualifier("dataSource")
//    private DataSource dataSource;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private PasswordEncoder oauthClientPasswordEncoder;
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new JdbcTokenStore(dataSource);
//    }
//
//    @Bean
//    public OAuth2AccessDeniedHandler oauthAccessDeniedHandler() {
//        return new OAuth2AccessDeniedHandler();
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
//        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").passwordEncoder(oauthClientPasswordEncoder);
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.jdbc(dataSource);
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager).userDetailsService(userDetailsService);
//    }
//}