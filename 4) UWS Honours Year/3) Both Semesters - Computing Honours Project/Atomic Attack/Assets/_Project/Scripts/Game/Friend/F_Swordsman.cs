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
    [SerializeField] protected Color ColourAluminium;

    void Reset()
    {
        MovementSpeed = 1.20f;
           AttackRate = 1.00f;
           LookRadius = 3.00f;
         AttackRadius = 0.85f;
    }

    protected override void Movement()
    {   // Activating the Steriods.
        if (Boron == true)      { MovementSpeed = MovementSpeed * 2; }
        if (Aluminium == true)  {  AttackDamage = 2; AttackRate = 0.5f; SpriteRenderer.color = ColourAluminium; }
        if (Aluminium == false) {  AttackDamage = 1; AttackRate = 1;    SpriteRenderer.color = new Color(255, 255, 255); } /* White */
        base.Movement();
    }

    void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.tag == "Enemy")
        {   // When F_Swordsman plays Attack.anim, will make the DamageArea active briefly to damage Target.
            // Will damage Target's Health with F_Swordsman's AttackDamage.
            collision.GetComponent<HealthSystem>().DamageTaken(AttackDamage);
        }
    }

    void OnCollisionEnter2D(Collision2D collision)
    { if (collision.gameObject.tag == "Ground") { Grounded = true; } GrabbedByMouse = false; }
}