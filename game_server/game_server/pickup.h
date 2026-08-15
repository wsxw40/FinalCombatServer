#pragma once

#define MAXTIME 9999
class PickUpObject
{
public:
	PickUpObject();

	virtual ~PickUpObject();

public:
	// on create
	virtual void Create();

	// on destroy
	virtual void Destroy();

	// update
	void Update(float delta);

	// is active
	bool IsActive();

public:
	uint uid;

	// Disappear time
	float time;
	bool auto_destroy;

	Vector3 position;
	float rotation;

private:
	byte state;
};

class DroppedWeapon : public PickUpObject
{
public:
	virtual void Create();

	virtual void Destroy();

public:
	Weapon weapon;
};


class SupplyObject : public PickUpObject
{
public:
	SupplyObject();

public:
	virtual void Create();

	virtual void Destroy();

public:
	Supply  supply;
	float	createtime;
};

class DroppedSupply : public PickUpObject
{
public:
	virtual void Create();

	virtual void Destroy();
public:
	byte	type;
	int		value;
};