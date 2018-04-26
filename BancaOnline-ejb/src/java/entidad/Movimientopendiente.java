/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author javier
 */
@Entity
@Table(name = "movimientopendiente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimientopendiente.findAll", query = "SELECT m FROM Movimientopendiente m")
    , @NamedQuery(name = "Movimientopendiente.findById", query = "SELECT m FROM Movimientopendiente m WHERE m.id = :id")
    , @NamedQuery(name = "Movimientopendiente.findByConcepto", query = "SELECT m FROM Movimientopendiente m WHERE m.concepto = :concepto")
    , @NamedQuery(name = "Movimientopendiente.findByFechaCreado", query = "SELECT m FROM Movimientopendiente m WHERE m.fechaCreado = :fechaCreado")
    , @NamedQuery(name = "Movimientopendiente.findByCuantia", query = "SELECT m FROM Movimientopendiente m WHERE m.cuantia = :cuantia")
    , @NamedQuery(name = "Movimientopendiente.findByDecimales", query = "SELECT m FROM Movimientopendiente m WHERE m.decimales = :decimales")
    , @NamedQuery(name = "Movimientopendiente.findByDivisa", query = "SELECT m FROM Movimientopendiente m WHERE m.divisa = :divisa")
    , @NamedQuery(name = "Movimientopendiente.findBySaldoRemitentePrevio", query = "SELECT m FROM Movimientopendiente m WHERE m.saldoRemitentePrevio = :saldoRemitentePrevio")})
public class Movimientopendiente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 200)
    @Column(name = "concepto")
    private String concepto;
    @Column(name = "fechaCreado")
    private BigInteger fechaCreado;
    @Column(name = "cuantia")
    private BigInteger cuantia;
    @Column(name = "decimales")
    private Integer decimales;
    @Size(max = 3)
    @Column(name = "divisa")
    private String divisa;
    @Column(name = "saldoRemitentePrevio")
    private BigInteger saldoRemitentePrevio;
    @JoinColumn(name = "remitente", referencedColumnName = "id")
    @ManyToOne
    private Cuentacorriente remitente;
    @JoinColumn(name = "receptor", referencedColumnName = "id")
    @ManyToOne
    private Cuentacorriente receptor;

    public Movimientopendiente() {
    }

    public Movimientopendiente(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigInteger getFechaCreado() {
        return fechaCreado;
    }

    public void setFechaCreado(BigInteger fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public BigInteger getCuantia() {
        return cuantia;
    }

    public void setCuantia(BigInteger cuantia) {
        this.cuantia = cuantia;
    }

    public Integer getDecimales() {
        return decimales;
    }

    public void setDecimales(Integer decimales) {
        this.decimales = decimales;
    }

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public BigInteger getSaldoRemitentePrevio() {
        return saldoRemitentePrevio;
    }

    public void setSaldoRemitentePrevio(BigInteger saldoRemitentePrevio) {
        this.saldoRemitentePrevio = saldoRemitentePrevio;
    }

    public Cuentacorriente getRemitente() {
        return remitente;
    }

    public void setRemitente(Cuentacorriente remitente) {
        this.remitente = remitente;
    }

    public Cuentacorriente getReceptor() {
        return receptor;
    }

    public void setReceptor(Cuentacorriente receptor) {
        this.receptor = receptor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientopendiente)) {
            return false;
        }
        Movimientopendiente other = (Movimientopendiente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Movimientopendiente[ id=" + id + " ]";
    }
    
}
