/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.modelo;

/**
 *
 * @author Horacio
 */
public class Socio {
    private String nombre;
    private String sexo;
    private int edad;
    private String comuna_residencia;
    private int codigo_comuna_residencia;
    private String comuna_antigua;
    private int codigo_comuna_antigua;
    private String nacionalidad;
    private String educacion;
    private int miembros_hogar;
    private int proveedor_principal;
    private int personas_trabajando;
    private int personas_buscando;
    private int otro_hogar;
    private int experiencia_años;
    private int ocupacion_aparte;
    private String actividad_principal;
    private int mas_puerto;
    private String afiliado;
    private String Afp;
    private int cotizacion;
    private String canal_informacion;
    private String codigo;
    private String rut_socio;
    private String fecha_nacimiento;
    private String casa_propia;
    private int isapre;
    private String direccion;
    private int numero_puertos;
    private int familia_puertos;
    private String rut_familiar_uno;
    private String rut_familiar_dos;

    public Socio() {
    }

    public Socio(String nombre, String sexo, int edad, String comuna_residencia, int codigo_comuna_residencia, String comuna_antigua, int codigo_comuna_antigua, String nacionalidad, String educacion, int miembros_hogar, int proveedor_principal, int personas_trabajando, int personas_buscando, int otro_hogar, int experiencia_años, int ocupacion_aparte, String actividad_principal, int mas_puerto, String afiliado, String Afp, int cotizacion, String canal_informacion, String codigo, String rut_socio, String fecha_nacimiento, String casa_propia, int isapre, String direccion, int numero_puertos, int familia_puertos, String rut_familiar_uno, String rut_familiar_dos) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
        this.comuna_residencia = comuna_residencia;
        this.codigo_comuna_residencia = codigo_comuna_residencia;
        this.comuna_antigua = comuna_antigua;
        this.codigo_comuna_antigua = codigo_comuna_antigua;
        this.nacionalidad = nacionalidad;
        this.educacion = educacion;
        this.miembros_hogar = miembros_hogar;
        this.proveedor_principal = proveedor_principal;
        this.personas_trabajando = personas_trabajando;
        this.personas_buscando = personas_buscando;
        this.otro_hogar = otro_hogar;
        this.experiencia_años = experiencia_años;
        this.ocupacion_aparte = ocupacion_aparte;
        this.actividad_principal = actividad_principal;
        this.mas_puerto = mas_puerto;
        this.afiliado = afiliado;
        this.Afp = Afp;
        this.cotizacion = cotizacion;
        this.canal_informacion = canal_informacion;
        this.codigo = codigo;
        this.rut_socio = rut_socio;
        this.fecha_nacimiento = fecha_nacimiento;
        this.casa_propia = casa_propia;
        this.isapre = isapre;
        this.direccion = direccion;
        this.numero_puertos = numero_puertos;
        this.familia_puertos = familia_puertos;
        this.rut_familiar_uno = rut_familiar_uno;
        this.rut_familiar_dos = rut_familiar_dos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getComuna_residencia() {
        return comuna_residencia;
    }

    public void setComuna_residencia(String comuna_residencia) {
        this.comuna_residencia = comuna_residencia;
    }

    public int getCodigo_comuna_residencia() {
        return codigo_comuna_residencia;
    }

    public void setCodigo_comuna_residencia(int codigo_comuna_residencia) {
        this.codigo_comuna_residencia = codigo_comuna_residencia;
    }

    public String getComuna_antigua() {
        return comuna_antigua;
    }

    public void setComuna_antigua(String comuna_antigua) {
        this.comuna_antigua = comuna_antigua;
    }

    public int getCodigo_comuna_antigua() {
        return codigo_comuna_antigua;
    }

    public void setCodigo_comuna_antigua(int codigo_comuna_antigua) {
        this.codigo_comuna_antigua = codigo_comuna_antigua;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEducacion() {
        return educacion;
    }

    public void setEducacion(String educacion) {
        this.educacion = educacion;
    }

    public int getMiembros_hogar() {
        return miembros_hogar;
    }

    public void setMiembros_hogar(int miembros_hogar) {
        this.miembros_hogar = miembros_hogar;
    }

    public int getProveedor_principal() {
        return proveedor_principal;
    }

    public void setProveedor_principal(int proveedor_principal) {
        this.proveedor_principal = proveedor_principal;
    }

    public int getPersonas_trabajando() {
        return personas_trabajando;
    }

    public void setPersonas_trabajando(int personas_trabajando) {
        this.personas_trabajando = personas_trabajando;
    }

    public int getPersonas_buscando() {
        return personas_buscando;
    }

    public void setPersonas_buscando(int personas_buscando) {
        this.personas_buscando = personas_buscando;
    }

    public int getOtro_hogar() {
        return otro_hogar;
    }

    public void setOtro_hogar(int otro_hogar) {
        this.otro_hogar = otro_hogar;
    }

    public int getExperiencia_años() {
        return experiencia_años;
    }

    public void setExperiencia_años(int experiencia_años) {
        this.experiencia_años = experiencia_años;
    }

    public int getOcupacion_aparte() {
        return ocupacion_aparte;
    }

    public void setOcupacion_aparte(int ocupacion_aparte) {
        this.ocupacion_aparte = ocupacion_aparte;
    }

    public String getActividad_principal() {
        return actividad_principal;
    }

    public void setActividad_principal(String actividad_principal) {
        this.actividad_principal = actividad_principal;
    }

    public int getMas_puerto() {
        return mas_puerto;
    }

    public void setMas_puerto(int mas_puerto) {
        this.mas_puerto = mas_puerto;
    }

    public String getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(String afiliado) {
        this.afiliado = afiliado;
    }

    public String getAfp() {
        return Afp;
    }

    public void setAfp(String Afp) {
        this.Afp = Afp;
    }

    public int getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(int cotizacion) {
        this.cotizacion = cotizacion;
    }

    public String getCanal_informacion() {
        return canal_informacion;
    }

    public void setCanal_informacion(String canal_informacion) {
        this.canal_informacion = canal_informacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRut_socio() {
        return rut_socio;
    }

    public void setRut_socio(String rut_socio) {
        this.rut_socio = rut_socio;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCasa_propia() {
        return casa_propia;
    }

    public void setCasa_propia(String casa_propia) {
        this.casa_propia = casa_propia;
    }

    public int getIsapre() {
        return isapre;
    }

    public void setIsapre(int isapre) {
        this.isapre = isapre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumero_puertos() {
        return numero_puertos;
    }

    public void setNumero_puertos(int numero_puertos) {
        this.numero_puertos = numero_puertos;
    }

    public int getFamilia_puertos() {
        return familia_puertos;
    }

    public void setFamilia_puertos(int familia_puertos) {
        this.familia_puertos = familia_puertos;
    }

    public String getRut_familiar_uno() {
        return rut_familiar_uno;
    }

    public void setRut_familiar_uno(String rut_familiar_uno) {
        this.rut_familiar_uno = rut_familiar_uno;
    }

    public String getRut_familiar_dos() {
        return rut_familiar_dos;
    }

    public void setRut_familiar_dos(String rut_familiar_dos) {
        this.rut_familiar_dos = rut_familiar_dos;
    }

     
}
