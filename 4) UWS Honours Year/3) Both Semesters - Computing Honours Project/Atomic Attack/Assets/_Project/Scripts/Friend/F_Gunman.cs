using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F_Gunman : Friend {
    // Child Class F_Gunman inheriting from Friend.
    [Space(10), Header("[^ Child: Friend ]")]
    [Space(10), Header("[^ Child: Gunman ] Attack")]
    [SerializeField] GameObject Projectile;
    [SerializeField] Transform FireLocation;
    [SerializeField] GameObject MuzzleFlashEffect;



    void Reset()
    {
        CostValue = 0;
        ScoreValue = 2000;
        StartHealth = 114;
        CurrentHealth = StartHealth;
        MovementSpeed = 1f;
        AttackDamage = 1;
        LookRadius = 5f;
        AttackRadius = LookRadius;
        AttackRate = 3f;
    }

    protected override void DamageTaken()
    {
        throw new System.NotImplementedException();
    }
}