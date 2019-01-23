using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F_Swordsman : Friend {
    // Child Class F_Swordsman inheriting from Friend.
    [Space(10), Header("[^ Child: Friend ]")]
    [Space(10), Header("[^ Child: Swordsman ] Attack")]
    [SerializeField] GameObject DamageArea;


    void Reset()
    {
        CostValue = 0;
        ScoreValue = 2000;
        MovementSpeed = 4f; //1.2
        RunAwayTimer = 3f;
        AttackDamage = 1;
        LookRadius = 3.5f;
        AttackRadius = 1.3f;
        AttackRate = 1f;
    }

    void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.tag == "Enemy")
        {   // When F_Swordsman plays Attack.anim, will make the DamageArea active briefly to damage Target.
            // Will damage Target's Health with the value of F_Swordsman's AttackDamage.
            collision.GetComponent<HealthSystem>().DamageTaken(AttackDamage);



            // If there's one f_swordsman vs one e_gunman, health system works.
            // If there's one f_swordsman vs one e_swordsman, get null reference exception after one hit???
        }
    }
}