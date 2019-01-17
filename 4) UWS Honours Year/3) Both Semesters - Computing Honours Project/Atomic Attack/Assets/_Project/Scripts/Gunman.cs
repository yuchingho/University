﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Gunman : AI_Human {

    [Space(-10), Header("[^ Child: Gunman ] Attack")]
    [SerializeField] float AttackDamage;
    [SerializeField] float AttackSpeed;
    [SerializeField] float AttackCooldown;
    [SerializeField] GameObject TargetImpact;
    [SerializeField] GameObject Projectile;
    [SerializeField] Transform FireLocation;
    [SerializeField] GameObject MuzzleFlash;

    void Reset()
    {
        MovementSpeed = 1f;
        LookRadius = 5f;
        AttackRadius = LookRadius;
    }

    /*
    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Ground")
        {
            Destroy(GetComponent<SpawnOnCastle>());
        }
    }
    */
}