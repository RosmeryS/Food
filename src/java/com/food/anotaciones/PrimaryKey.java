package com.food.anotaciones;

//<editor-fold defaultstate="collapsed" desc="Imports.">
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//</editor-fold>

/**
 * Anotación que se utiliza para manejar variables que representan las llaves primarias de la tabla que representan.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PrimaryKey {

}
