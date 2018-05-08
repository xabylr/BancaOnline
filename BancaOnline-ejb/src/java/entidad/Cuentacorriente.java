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
@Table(name = "cuentacorriente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuentacorriente.findAll", query = "SELECT c FROM Cuentacorriente c")
    , @NamedQuery(name = "Cuentacorriente.findById", query = "SELECT c FROM Cuentacorriente c WHERE c.id = :id")
    , @NamedQuery(name = "Cuentacorriente.findByEntidad", query = "SELECT c FROM Cuentacorriente c WHERE c.entidad = :entidad")
    , @NamedQuery(name = "Cuentacorriente.findByOficina", query = "SELECT c FROM Cuentacorriente c WHERE c.oficina = :oficina")
    , @NamedQuery(name = "Cuentacorriente.findByCc", query = "SELECT c FROM Cuentacorriente c WHERE c.cc = :cc")
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
    @Column(name = "entidad")
    private Short entidad;
    @Column(name = "oficina")
    private Short oficina;
    @Column(name = "cc")
    private Long cc;
    @Column(name = "saldo")
    private BigInteger saldo;
    @Column(name = "decimales")
    private Integer decimales;
    @Size(max = 3)
    @Column(name = "divisa")
    private String divisa;
    @Column(name = "fechacreacion")
    private BigInteger fechacreacion;

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

    public Short getEntidad() {
        return entidad;
    }

    public void setEntidad(Short entidad) {
        this.entidad = entidad;
    }

    public Short getOficina() {
        return oficina;
    }

    public void setOficina(Short oficina) {
        this.oficina = oficina;
    }

    public Long getCc() {
        return cc;
    }

    public void setCc(Long cc) {
        this.cc = cc;
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
