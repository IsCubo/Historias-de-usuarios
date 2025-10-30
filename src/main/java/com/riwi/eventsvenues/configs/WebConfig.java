package com.riwi.eventsvenues.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase de configuración global para habilitar CORS y otras configuraciones de Spring Web MVC.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configuración de CORS (Cross-Origin Resource Sharing) para toda la aplicación.
     * @param registry El registro de configuración de CORS.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Habilita CORS para todos los endpoints bajo /api/v1/**
        registry.addMapping("/api/v1/**")
                // Permite solicitudes desde cualquier origen. Para producción, se recomienda
                // reemplazar "*" por los dominios específicos (ej. "http://mi-frontend.com").
                .allowedOrigins("*")
                // Permite los métodos HTTP comunes: GET, POST, PUT, DELETE.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // Permite todos los encabezados HTTP en la solicitud.
                .allowedHeaders("*")
                // Permite el envío de cookies de autenticación, etc. (importante si usas sesiones/tokens con cookies).
                .allowCredentials(false)
                // Tiempo en segundos que la respuesta de pre-flight (OPTIONS) puede ser cacheada.
                .maxAge(3600);
    }
}
