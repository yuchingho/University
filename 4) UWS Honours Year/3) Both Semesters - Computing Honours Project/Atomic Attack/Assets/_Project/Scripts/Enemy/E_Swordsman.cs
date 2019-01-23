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
        MovementSpeed = 1.2f;
        RunAwayTimer = 3f;
        AttackDamage = 1;
        LookRadius = 3.5f;
        AttackRadius = 1.3f;
        AttackRate = 1f;
    }

    void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.tag == "Friend")
        {   // When E_Swordsman plays Attack.anim, will make the DamageArea active briefly to damage Target.
            // Will damage Target's Health with the value of E_Swordsman's AttackDamage.
            //collision.GetComponent<HealthSystem>().DamageTaken(AttackDamage);
        }
    }
}