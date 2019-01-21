using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class E_Swordsman : Enemy {
    // Child Class E_Swordsman inheriting from Enemy.
    [Space(10), Header("[^ Child: Enemy ]")]
    [Space(10), Header("[^ Child: Swordsman ] Attack")]
    [SerializeField] GameObject DamageArea;
    [SerializeField] bool Stunned;

    void Reset()
    {
        CostValue = 0;
        ScoreValue = 2000;
        StartHealth = 100;
        CurrentHealth = StartHealth;
        MovementSpeed = 1.2f;
        AttackDamage = 1;
        LookRadius = 3f;
        AttackRadius = 1.3f;
        AttackRate = 2f;
    }

    protected override void DamageTaken()
    {
        throw new System.NotImplementedException();
    }
}