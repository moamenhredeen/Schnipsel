package me.moamenhredeen.kakera;

import jakarta.security.enterprise.authentication.mechanism.http.OpenIdAuthenticationMechanismDefinition;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@OpenIdAuthenticationMechanismDefinition(
)
public class KakeraApplication extends Application {

}