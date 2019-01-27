using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class E_Swordsman : AI_Enemy {
    // Child Class E_Swordsman inheriting from Enemy.
    [Space(10), Header("[^ Child: Enemy ]")]
    [Space(10), Header("[^ Child: Swordsman ] Attack")]
    [SerializeField] bool Stunned;

    void Reset()
    {   // Health = 6;
        CostValue = 0;
        ScoreValue = 1;
        MovementSpeed = 1.2f;
        RunAwayTimer = 3f;
        AttackDamage = 1;
        LookRadius = 3f;
        AttackRadius = 1.3f;
        AttackRate = 1f;
    }

    void OnTriggerEnter2D(Collider2D collision)
    {
        try
        {
            if (collision.gameObject.tag == "Friend")
            {   // When E_Swordsman plays Attack.anim, will make the DamageArea active briefly to damage Target.
                // Will damage Target's Health with E_Swordsman's AttackDamage.
                collision.GetComponent<HealthSystem>().DamageTaken(AttackDamage);

                // If there's one f_swordsman vs one e_gunman, health system works.
                // If there's one f_swordsman vs one e_swordsman, get null reference exception after one hit?
            }
        }
        catch (System.NullReferenceException) { };
    }
}