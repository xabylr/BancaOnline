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
@Table(name = "movimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimiento.findAll", query = "SELECT m FROM Movimiento m")
    , @NamedQuery(name = "Movimiento.findById", query = "SELECT m FROM Movimiento m WHERE m.id = :id")
    , @NamedQuery(name = "Movimiento.findByConcepto", query = "SELECT m FROM Movimiento m WHERE m.concepto = :concepto")
    , @NamedQuery(name = "Movimiento.findByFecha", query = "SELECT m FROM Movimiento m WHERE m.fecha = :fecha")
    , @NamedQuery(name = "Movimiento.findByCuantia", query = "SELECT m FROM Movimiento m WHERE m.cuantia = :cuantia")
    , @NamedQuery(name = "Movimiento.findByDecimales", query = "SELECT m FROM Movimiento m WHERE m.decimales = :decimales")
    , @NamedQuery(name = "Movimiento.findByDivisa", query = "SELECT m FROM Movimiento m WHERE m.divisa = :divisa")
    , @NamedQuery(name = "Movimiento.findBySaldoRttPrev", query = "SELECT m FROM Movimiento m WHERE m.saldoRttPrev = :saldoRttPrev")
    , @NamedQuery(name = "Movimiento.findBySaldoRttPrevDec", query = "SELECT m FROM Movimiento m WHERE m.saldoRttPrevDec = :saldoRttPrevDec")
    , @NamedQuery(name = "Movimiento.findBySaldoRttPrevDiv", query = "SELECT m FROM Movimiento m WHERE m.saldoRttPrevDiv = :saldoRttPrevDiv")
    , @NamedQuery(name = "Movimiento.findBySaldoRcpPrev", query = "SELECT m FROM Movimiento m WHERE m.saldoRcpPrev = :saldoRcpPrev")
    , @NamedQuery(name = "Movimiento.findBySaldoRcpPrevDec", query = "SELECT m FROM Movimiento m WHERE m.saldoRcpPrevDec = :saldoRcpPrevDec")
    , @NamedQuery(name = "Movimiento.findBySaldoRcpPrevDiv", query = "SELECT m FROM Movimiento m WHERE m.saldoRcpPrevDiv = :saldoRcpPrevDiv")})
public class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 200)
    @Column(name = "concepto")
    private String concepto;
    @Column(name = "fecha")
    private BigInteger fecha;
    @Column(name = "cuantia")
    private BigInteger cuantia;
    @Column(name = "decimales")
    private Integer decimales;
    @Size(max = 3)
    @Column(name = "divisa")
    private String divisa;
    @Column(name = "saldoRttPrev")
    private BigInteger saldoRttPrev;
    @Column(name = "saldoRttPrevDec")
    private Integer saldoRttPrevDec;
    @Size(max = 3)
    @Column(name = "saldoRttPrevDiv")
    private String saldoRttPrevDiv;
    @Column(name = "saldoRcpPrev")
    private BigInteger saldoRcpPrev;
    @Column(name = "saldoRcpPrevDec")
    private Integer saldoRcpPrevDec;
    @Size(max = 3)
    @Column(name = "saldoRcpPrevDiv")
    private String saldoRcpPrevDiv;
    @JoinColumn(name = "remitente", referencedColumnName = "id")
    @ManyToOne
    private Cuentacorriente remitente;
    @JoinColumn(name = "receptor", referencedColumnName = "id")
    @ManyToOne
    private Cuentacorriente receptor;

    public Movimiento() {
    }

    public Movimiento(Long id) {
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

    public BigInteger getFecha() {
        return fecha;
    }

    public void setFecha(BigInteger fecha) {
        this.fecha = fecha;
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

    public BigInteger getSaldoRttPrev() {
        return saldoRttPrev;
    }

    public void setSaldoRttPrev(BigInteger saldoRttPrev) {
        this.saldoRttPrev = saldoRttPrev;
    }

    public Integer getSaldoRttPrevDec() {
        return saldoRttPrevDec;
    }

    public void setSaldoRttPrevDec(Integer saldoRttPrevDec) {
        this.saldoRttPrevDec = saldoRttPrevDec;
    }

    public String getSaldoRttPrevDiv() {
        return saldoRttPrevDiv;
    }

    public void setSaldoRttPrevDiv(String saldoRttPrevDiv) {
        this.saldoRttPrevDiv = saldoRttPrevDiv;
    }

    public BigInteger getSaldoRcpPrev() {
        return saldoRcpPrev;
    }

    public void setSaldoRcpPrev(BigInteger saldoRcpPrev) {
        this.saldoRcpPrev = saldoRcpPrev;
    }

    public Integer getSaldoRcpPrevDec() {
        return saldoRcpPrevDec;
    }

    public void setSaldoRcpPrevDec(Integer saldoRcpPrevDec) {
        this.saldoRcpPrevDec = saldoRcpPrevDec;
    }

    public String getSaldoRcpPrevDiv() {
        return saldoRcpPrevDiv;
    }

    public void setSaldoRcpPrevDiv(String saldoRcpPrevDiv) {
        this.saldoRcpPrevDiv = saldoRcpPrevDiv;
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
        if (!(object instanceof Movimiento)) {
            return false;
        }
        Movimiento other = (Movimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Movimiento[ id=" + id + " ]";
    }
    
}
