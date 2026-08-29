package com.pearl.fcw.lobby.pojo.json;

/**
 * 道具瞄准信息
 */
public class JsonItemSight {
	private float fov;//kov
	private float mouseSensitivity;//鼠标灵敏度
	private float moveSpeedAdd;//开镜后移动速度变化
	private float recoilReduceFactor;//开镜后后坐力缩小倍数
	private float shootSpeedReduceFactor;//开镜后射速减慢倍数

	public float getFov() {
		return fov;
	}

	public void setFov(float fov) {
		this.fov = fov;
	}

	public float getMouseSensitivity() {
		return mouseSensitivity;
	}

	public void setMouseSensitivity(float mouseSensitivity) {
		this.mouseSensitivity = mouseSensitivity;
	}

	public float getMoveSpeedAdd() {
		return moveSpeedAdd;
	}

	public void setMoveSpeedAdd(float moveSpeedAdd) {
		this.moveSpeedAdd = moveSpeedAdd;
	}

	public float getRecoilReduceFactor() {
		return recoilReduceFactor;
	}

	public void setRecoilReduceFactor(float recoilReduceFactor) {
		this.recoilReduceFactor = recoilReduceFactor;
	}

	public float getShootSpeedReduceFactor() {
		return shootSpeedReduceFactor;
	}

	public void setShootSpeedReduceFactor(float shootSpeedReduceFactor) {
		this.shootSpeedReduceFactor = shootSpeedReduceFactor;
	}
}
