using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F_Gunman : AI_Friend {
    // Child Class F_Gunman inheriting from Friend.
    [Space(10), Header("[^ Child: Friend ]")]
    [Space(10), Header("[^ Child: Gunman ] Attack")]
    [SerializeField] GameObject Projectile;
    [SerializeField] Transform FireLocation;
    [SerializeField] GameObject MuzzleFlashEffect;




    void Reset()
    {   // Health = 6;
        CostValue = 0;
        ScoreValue = 0;
        MovementSpeed = 1f;
        RunAwayTimer = 3f;
        AttackDamage = 3;
        LookRadius = 4f;
        AttackRadius = 4f;
        AttackRate = 0.75f;
    }
}