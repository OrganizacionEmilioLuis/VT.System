package vt.system.Activity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Miler Pacheco 3/31/17.
 */

public class registro {
    @SerializedName("imagen_smartphone")
    @Expose
    private String imagen_smartphone;

    @SerializedName("nombre_cliente")
    @Expose
    private String nombre_cliente;

    @SerializedName("telefono_cliente")
    @Expose
    private String telefono_cliente;

    @SerializedName("marca_smartphone")
    @Expose
    private String marca_smartphone;

    @SerializedName("modelo_smartphone")
    @Expose
    private String modelo_smartphone;

    @SerializedName("imei1_smartphone")
    @Expose
    private String imei1_smartphone;

    @SerializedName("imei2_smartphone")
    @Expose
    private String imei2_smartphone;

    @SerializedName("observacion")
    @Expose
    private String observacion;

    public registro() {
    }

    public registro(String imagen_smartphone, String nombre_cliente, String telefono_cliente, String marca_smartphone, String modelo_smartphone, String imei1_smartphone, String imei2_smartphone, String observacion ) {
        this.imagen_smartphone = imagen_smartphone;
        this.nombre_cliente = nombre_cliente;
        this.telefono_cliente = telefono_cliente;
        this.marca_smartphone = marca_smartphone;
        this.modelo_smartphone = modelo_smartphone;
        this.imei1_smartphone = imei1_smartphone;
        this.imei2_smartphone = imei2_smartphone;
        this.observacion = observacion;
    }

    public String getImagen_smartphone() {return imagen_smartphone;
    }

    public void setImagen_smartphone(String imagen_smartphone) { this.imagen_smartphone = imagen_smartphone;
    }

    public String getNombre_cliente() {return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {this.nombre_cliente = nombre_cliente;
    }

    public String getTelefono_cliente() {return telefono_cliente;
    }

    public void setTelefono_cliente(String telefono_cliente) {this.telefono_cliente = telefono_cliente;
    }

    public String getMarca_smartphone() {return marca_smartphone;
    }

    public void setMarca_smartphone(String marca_smartphone) {this.marca_smartphone = marca_smartphone;
    }

    public String getModelo_smartphone() {return modelo_smartphone;
    }

    public void setImei1_smartphone(String imei1_smartphone) {this.imei1_smartphone = imei1_smartphone;
    }

    public String getImei1_smartphone() {return imei1_smartphone;
    }

    public String getImei2_smartphone() {return imei2_smartphone;
    }

    public void setImei2_smartphone(String imei2_smartphone) {this.imei2_smartphone = imei2_smartphone;
    }

    public String getObservacion() {return observacion;
    }

    public void setObservacion(String observacion) {this.observacion = observacion;
    }

}
