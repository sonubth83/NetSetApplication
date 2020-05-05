package in.nit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shiptab")
public class ShipmentType {
	@Id
	@GeneratedValue
	@Column(name = "sid")
	private Integer shipId;
	
	@Column(name = "smode")
	private String shipMode;
	
	@Column(name = "scode")
	private String shipCode;
	
	@Column(name = "senb")
	private String enbShip;
	
	@Column(name = "sgrad")
	private String shipGrad;
	
	@Column(name = "sdesc")
	private String shipDesc;

	public ShipmentType() {
		super();
	}

	public ShipmentType(Integer shipId) {
		super();
		this.shipId = shipId;
	}

	public Integer getShipId() {
		return shipId;
	}

	public void setShipId(Integer shipId) {
		this.shipId = shipId;
	}

	public String getShipMode() {
		return shipMode;
	}

	public void setShipMode(String shipMode) {
		this.shipMode = shipMode;
	}

	public String getShipCode() {
		return shipCode;
	}

	public void setShipCode(String shipCode) {
		this.shipCode = shipCode;
	}

	public String getEnbShip() {
		return enbShip;
	}

	public void setEnbShip(String enbShip) {
		this.enbShip = enbShip;
	}

	public String getShipGrad() {
		return shipGrad;
	}

	public void setShipGrad(String shipGrad) {
		this.shipGrad = shipGrad;
	}

	public String getShipDesc() {
		return shipDesc;
	}

	public void setShipDesc(String shipDesc) {
		this.shipDesc = shipDesc;
	}

	@Override
	public String toString() {
		return "ShipmentType [shipId=" + shipId + ", shipMode=" + shipMode + ", shipCode=" + shipCode + ", enbShip="
				+ enbShip + ", shipGrad=" + shipGrad + ", shipDesc=" + shipDesc + "]";
	}

}
