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
        MovementSpeed = 1f;
        RunAwayTimer = 3f;
        AttackDamage = 1;
        LookRadius = 5f;
        AttackRadius = LookRadius;
        AttackRate = 0.75f;
    }
}