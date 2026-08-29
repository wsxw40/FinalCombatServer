package com.pearl.o2o.pojo;

import java.io.OutputStream;

import com.pearl.o2o.pojo.jaxb.HeroWeapon;
import com.pearl.o2o.utils.BinaryUtil;

public class Hero extends BasePojo {

	private static final long serialVersionUID = 4381879920570302297L;

	public void writeByte(OutputStream out) throws Exception {
		// Resources
		String[] costume = this.resoures;
		int size = costume.length;

		out.write(BinaryUtil.toByta((byte) 1));

		out.write(BinaryUtil.toByta(size));
		for (String cos : costume) {
			if (cos != null && !"".equals(cos)) {
				out.write(BinaryUtil.toByta(cos));
			}
		}
		mainWeapon.writeByte(out);
		subWeapon.writeByte(out);
	}

	private String[] resoures;
	private SysItem mainWeapon;
	private SysItem subWeapon;

	public String[] getResoures() {
		return resoures;
	}

	
	public SysItem getMainWeapon() {
		return mainWeapon;
	}


	public void setMainWeapon(SysItem mainWeapon) {
		this.mainWeapon = mainWeapon;
	}


	public SysItem getSubWeapon() {
		return subWeapon;
	}


	public void setSubWeapon(SysItem subWeapon) {
		this.subWeapon = subWeapon;
	}


	public void setResoures(String[] resoures) {
		this.resoures = resoures;
	}

}
