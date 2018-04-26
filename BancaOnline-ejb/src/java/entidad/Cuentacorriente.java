/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author javier
 */
@Entity
@Table(name = "cuentacorriente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuentacorriente.findAll", query = "SELECT c FROM Cuentacorriente c")
    , @NamedQuery(name = "Cuentacorriente.findById", query = "SELECT c FROM Cuentacorriente c WHERE c.id = :id")
    , @NamedQuery(name = "Cuentacorriente.findByIban", query = "SELECT c FROM Cuentacorriente c WHERE c.iban = :iban")
    , @NamedQuery(name = "Cuentacorriente.findBySaldo", query = "SELECT c FROM Cuentacorriente c WHERE c.saldo = :saldo")
    , @NamedQuery(name = "Cuentacorriente.findByDecimales", query = "SELECT c FROM Cuentacorriente c WHERE c.decimales = :decimales")
    , @NamedQuery(name = "Cuentacorriente.findByDivisa", query = "SELECT c FROM Cuentacorriente c WHERE c.divisa = :divisa")
    , @NamedQuery(name = "Cuentacorriente.findByFechacreacion", query = "SELECT c FROM Cuentacorriente c WHERE c.fechacreacion = :fechacreacion")})
public class Cuentacorriente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 24)
    @Column(name = "iban")
    private String iban;
    @Column(name = "saldo")
    private BigInteger saldo;
    @Column(name = "decimales")
    private Integer decimales;
    @Size(max = 3)
    @Column(name = "divisa")
    private String divisa;
    @Column(name = "fechacreacion")
    private BigInteger fechacreacion;
    @OneToMany(mappedBy = "remitente")
    private Collection<Movimientorealizado> movimientorealizadoCollection;
    @OneToMany(mappedBy = "receptor")
    private Collection<Movimientorealizado> movimientorealizadoCollection1;
    @OneToMany(mappedBy = "remitente")
    private Collection<Movimientopendiente> movimientopendienteCollection;
    @OneToMany(mappedBy = "receptor")
    private Collection<Movimientopendiente> movimientopendienteCollection1;

    public Cuentacorriente() {
    }

    public Cuentacorriente(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigInteger getSaldo() {
        return saldo;
    }

    public void setSaldo(BigInteger saldo) {
        this.saldo = saldo;
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

    public BigInteger getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(BigInteger fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    @XmlTransient
    public Collection<Movimientorealizado> getMovimientorealizadoCollection() {
        return movimientorealizadoCollection;
    }

    public void setMovimientorealizadoCollection(Collection<Movimientorealizado> movimientorealizadoCollection) {
        this.movimientorealizadoCollection = movimientorealizadoCollection;
    }

    @XmlTransient
    public Collection<Movimientorealizado> getMovimientorealizadoCollection1() {
        return movimientorealizadoCollection1;
    }

    public void setMovimientorealizadoCollection1(Collection<Movimientorealizado> movimientorealizadoCollection1) {
        this.movimientorealizadoCollection1 = movimientorealizadoCollection1;
    }

    @XmlTransient
    public Collection<Movimientopendiente> getMovimientopendienteCollection() {
        return movimientopendienteCollection;
    }

    public void setMovimientopendienteCollection(Collection<Movimientopendiente> movimientopendienteCollection) {
        this.movimientopendienteCollection = movimientopendienteCollection;
    }

    @XmlTransient
    public Collection<Movimientopendiente> getMovimientopendienteCollection1() {
        return movimientopendienteCollection1;
    }

    public void setMovimientopendienteCollection1(Collection<Movimientopendiente> movimientopendienteCollection1) {
        this.movimientopendienteCollection1 = movimientopendienteCollection1;
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
        if (!(object instanceof Cuentacorriente)) {
            return false;
        }
        Cuentacorriente other = (Cuentacorriente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Cuentacorriente[ id=" + id + " ]";
    }
    
}
