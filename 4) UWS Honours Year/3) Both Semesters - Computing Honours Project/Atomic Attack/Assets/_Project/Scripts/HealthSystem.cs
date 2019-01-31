﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HealthSystem : MonoBehaviour {

    public float Health;
    public bool Deceased = false;

    public void DamageTaken(float Damage)
    {
        Health -= Damage;
        if (Health <= 0)
        {
            Deceased = true;
        }
    }
}