package co.edu.uniandes.csw.stamps.config;

import java.util.Properties;

/**
 * Retrieves API Key Id and Secret from Environment Variables
 *
 * @author af.esguerra10
 */
public class ApiKeyProperties extends Properties {
    
    private static final String ID_PROP = "apiKey.id";
    private static final String SECRET_PROP = "apiKey.secret";

    public ApiKeyProperties() {
        super.put(ID_PROP, System.getenv("STORMPATH_API_KEY_ID"));
        super.put(SECRET_PROP, System.getenv("STORMPATH_API_KEY_SECRET"));
    }
    
    public String getApiKeyId(){
        return this.getProperty(ID_PROP);
    }
    
    public String getApiKeySecret(){
        return this.getProperty(SECRET_PROP);
    }
}
