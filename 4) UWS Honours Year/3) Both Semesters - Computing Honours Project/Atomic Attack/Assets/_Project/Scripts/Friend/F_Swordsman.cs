using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F_Swordsman : AI_Friend {
    [Space( 10), Header("[^ Child: AI_Friend ]")]
    // Child Class F_Swordsman inheriting from AI_Friend.
    [Space(-10), Header("[^ Child:   F_Swordsman ]")]
    #pragma warning disable
    [SerializeField] protected int AttackDamage = 1;

    [Space( 10), Header("[^ Child:   F_Swordsman ] Steriods")]
    public bool Boron;        // Add MovementSpeed for when activated.
    public bool Aluminium;    // Add AttackDamage and AttackRate for when activated.

    void Reset()
    {
        MovementSpeed = 1.2f;
        AttackRate = 1f;
        LookRadius = 3f;
        AttackRadius = 0.85f;
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Ground") { Grounded = true; } else { }
        // not detecting when the sprite has left the ground... else function doesn't work
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