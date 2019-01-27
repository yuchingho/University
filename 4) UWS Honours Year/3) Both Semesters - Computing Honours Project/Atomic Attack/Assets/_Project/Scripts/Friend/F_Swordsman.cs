using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F_Swordsman : AI_Friend {
    // Child Class F_Swordsman inheriting from Friend.

    
    
    
    void Reset()
    {   // Health = 6;
        CostValue = 0;
        ScoreValue = 0;
        MovementSpeed = 1.2f;
        RunAwayTimer = 3f;
        AttackDamage = 1;
        LookRadius = 3f;
        AttackRadius = 1.3f;
        AttackRate = 1f;
    }

    void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.tag == "Enemy")
        {   // When F_Swordsman plays Attack.anim, will make the DamageArea active briefly to damage Target.
            // Will damage Target's Health with F_Swordsman's AttackDamage.
            collision.GetComponent<HealthSystem>().DamageTaken(AttackDamage);

            // If there's one f_swordsman vs one e_gunman, health system works.
            // If there's one f_swordsman vs one e_swordsman, get null reference exception after one hit?
        }
    }
}