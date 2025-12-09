
package app.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            // Vincula a configuração de CORS definida no bean abaixo
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) 
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/token/**").permitAll()
                .requestMatchers("/api/cidade/findAll").permitAll()
                // Adicione OPTIONS aqui se o Spring Security estiver bloqueando o preflight antes do CORS filter
                // .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() 
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(new JwtConverter())));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // CORREÇÃO: Permitir credenciais (cookies/tokens auth headers)
        configuration.setAllowCredentials(true);
        
        // CORREÇÃO: Adicionadas as portas explicitamente conforme seu print de erro
        configuration.setAllowedOrigins(Arrays.asList(
            "https://app.kds.com.br",       // Acesso via domínio padrão (443)
            "https://app.kds.com.br:8443",  // <--- ESSA É A QUE ESTÁ FALTANDO NO SEU ERRO
            "http://app.kds.com.br",
            "http://192.168.15.6",
            "https://192.168.15.6",
            "https://192.168.15.6:8443"   
            // Por precaução, caso acesse via IP com porta
        ));

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH"));
        
        // Permitir todos os headers é o ideal para evitar bloqueio de tokens customizados
        configuration.setAllowedHeaders(Arrays.asList("*"));
        
        // Expor headers caso seu frontend precise ler headers de resposta (opcional, mas útil)
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}




////////////////////////////////////////////////////////////////////////////////////////////////////////////////







/*package app.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {
	
	
	
}

*/	
/*
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http    
		.csrf(AbstractHttpConfigurer::disable)
		.cors(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/api/login").permitAll() 
				.requestMatchers("/api/register").permitAll()
				.requestMatchers("/api/cidade/findAll").permitAll()
				.requestMatchers("/api/usuario/save").permitAll()
				.requestMatchers("/chat").permitAll()
				.anyRequest().authenticated())
		.authenticationProvider(authenticationProvider)
		
		.sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}
	
	*//*
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(false);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.setAllowedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION,HttpHeaders.CONTENT_TYPE,HttpHeaders.ACCEPT));
		config.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(),HttpMethod.POST.name(),HttpMethod.PUT.name(),HttpMethod.DELETE.name()));
		config.setMaxAge(3600L);
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
		bean.setOrder(-102);
		

		return bean;
	}
*//*
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    
	    // 🔥 CORREÇÃO: Mude para TRUE quando usar OAuth2/JWT
	    config.setAllowCredentials(true);
	    
	    // 🔥 CORREÇÃO: Especifique as origens explicitamente em vez de "*"
	    config.setAllowedOrigins(Arrays.asList(
	        "https://app.kds.com.br",
	        "http://app.kds.com.br", 
	        "http://10.35.228.247",
	        "https://10.35.228.247"
	    ));
	    
	    config.setAllowedHeaders(Arrays.asList(
	        HttpHeaders.AUTHORIZATION,
	        HttpHeaders.CONTENT_TYPE,
	        HttpHeaders.ACCEPT,
	        HttpHeaders.ORIGIN
	    ));
	    
	    config.setAllowedMethods(Arrays.asList(
	        HttpMethod.GET.name(),
	        HttpMethod.POST.name(), 
	        HttpMethod.PUT.name(),
	        HttpMethod.DELETE.name(),
	        HttpMethod.OPTIONS.name() // 🔥 IMPORTANTE: Adicione OPTIONS
	    ));
	    
	    config.setMaxAge(3600L);
	    source.registerCorsConfiguration("/**", config);
	    
	    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
	    bean.setOrder(-102);
	    return bean;
	}
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http
		.csrf(csrf -> csrf.disable())
		 .cors(cors -> cors.configurationSource(corsConfigurationSource()))
		 .authorizeHttpRequests(authz -> authz
		            .requestMatchers("/api/token/**").permitAll()  // ⬅️ PERMITE SEU TOKEN CONTROLLER
		            .requestMatchers("/api/cidade/findAll").permitAll() // ⬅️ PARA TESTE
		            .anyRequest().authenticated()
		        )
 
		 
		 
		.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(new JwtConverter())));
	
		
		
		return http.build();
	}
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowCredentials(true);
	    configuration.setAllowedOrigins(Arrays.asList(
	        "https://app.kds.com.br",
	        "http://app.kds.com.br",
	        "http://10.35.228.247", 
	        "https://10.35.228.247"
	    ));
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    configuration.setAllowedHeaders(Arrays.asList("*"));
	    
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
}
*/
