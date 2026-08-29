#include "pch.h"

enum state
{
	kIdle,
	kActive,
};

PickUpObject::PickUpObject()
{
	state = kIdle;
}

PickUpObject::~PickUpObject()
{
}

// on create
void PickUpObject::Create()
{
	state = kActive;
	time = server.config.gun_destroy_time;
	auto_destroy = false;
	position.x = position.y = position.z = 0;
	rotation = 0;
}

// on destroy
void PickUpObject::Destroy()
{
	state = kIdle;
}

// update
void PickUpObject::Update(float delta)
{
	if (IsActive())
	{
		if (auto_destroy)
		{
			time -= delta;

			if (time <= 0)
				Destroy();
		}
	}
}
// is active
bool PickUpObject::IsActive()
{
	return state == kActive;
}


void DroppedWeapon::Create()
{
	PickUpObject::Create();

	auto_destroy = true;
}

void DroppedWeapon::Destroy()
{
	if (IsActive())
	{
		// broadcast player drop gun
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c)
				c->DestroyDroppedWeapon(uid);
		}

		server.dropped_weapon.Free(uid);
	}

	PickUpObject::Destroy();
}

void DroppedSupply::Create()
{
	PickUpObject::Create();
	
	type = 0;
	value = 0;
}

void DroppedSupply::Destroy()
{
	PickUpObject::Destroy();
}

SupplyObject::SupplyObject()
{
	auto_destroy = false;
	createtime = MAXTIME;
}

void SupplyObject::Create()
{
	PickUpObject::Create();
}

void SupplyObject::Destroy()
{
	PickUpObject::Destroy();
}
