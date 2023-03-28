/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package readers;

import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author farid
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class WeatherForecast implements MessageBodyReader<List<Entities.WeatherForecast>>{
 
    @Override
    public boolean isReadable(Class<?> type, java.lang.reflect.Type type1, Annotation[] antns, MediaType mt) {
        return List.class.isAssignableFrom(type);
    }

    @Override
    public List<Entities.WeatherForecast> readFrom(
            Class<List<Entities.WeatherForecast>> type, 
            java.lang.reflect.Type type1, 
            Annotation[] antns, 
            MediaType mt, 
            MultivaluedMap<String, String> mm, 
            InputStream entityStream) throws IOException, WebApplicationException {
        
        List<Entities.WeatherForecast> lista = new ArrayList<>(); //creo mi contenedor de objetos
        try {
            Entities.WeatherForecast p = null; //sera mi contenedor por elemento del arreglo
            JsonParser parser = Json.createParser(entityStream); //creo mi procesador JSON desde el Stream
            while (parser.hasNext()) { //... recorro el stream  buscando sus elementos
                JsonParser.Event parserNext = parser.next();
                //LOGGER.log(Level.INFO, "parser.next:{0}", parserNext);
                switch (parserNext) {
                    case START_OBJECT -> //si es el comienzo de un objeto
                        p = new Entities.WeatherForecast(); //creo un elemento del arreglo, y guardare todo alli
                    case KEY_NAME -> {
                        //si es un nombre de atributo..
                        if (p == null) //...pero aun no se ha instanciado un objeto temporal
                        {
                            break;  // ... termino
                        }
                        String key = parser.getString(); //.. sino, obtengo el nombre lo obtengo
                        parser.next(); //... paso al sigueinte elemento
                        switch (key) { //... y dependiendo del nombre del atributo
                            case "date" -> p.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(parser.getString())); //... pongo el valor en el bean
                            case "temperatureC" -> p.setTemperatureC(parser.getInt());
                            case "temperatureF" -> p.setTemperatureF(parser.getInt());
                            case "summary" -> p.setSummary(parser.getString());
                        }
                        //... y dependiendo del nombre del atributo
                    }

                    case END_OBJECT -> {
                        // si termina un objeto
                        if (p != null) { //.. y se habia creado un objeto..
                            lista.add(p); // el elemento llenado, lo agrego a la lista
                            p = null; //.. e inicializo la variable temporal en NULL
                        }
                    }

                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(WeatherForecast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
            return lista;
    }
}
