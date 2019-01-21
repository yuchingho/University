using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class E_Gunman : Enemy {
    // Child Class E_Gunman inheriting from Enemy.
    [Space(10), Header("[^ Child: Enemy ]")]
    [Space(10), Header("[^ Child: Gunman ] Attack")]
    [SerializeField] GameObject Projectile;
    [SerializeField] bool Stunned;
    [SerializeField] bool Blinded;
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
        AttackRate = 1f;
    }

    protected override void DamageTaken()
    {
        throw new System.NotImplementedException();
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