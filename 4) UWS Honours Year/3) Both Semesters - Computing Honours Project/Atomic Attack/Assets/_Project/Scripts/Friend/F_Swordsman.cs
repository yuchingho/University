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
        StartHealth = 100;
        CurrentHealth = StartHealth;
        MovementSpeed = 4f; //1.2
        AttackDamage = 1;
        LookRadius = 3f;
        AttackRadius = 1.3f;
        AttackRate = 2f;
    }

    protected override void DamageTaken()
    {
        throw new System.NotImplementedException();
    }

    void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.tag == "Enemy")
        {   // When F_Swordsman plays Attack.anim, will make the DamageArea active briefly to damage Target.
            TargetHealth -= AttackDamage;
            Debug.Log("Swordslashed " + Target.name + ", HP = " + TargetHealth + ", " + Time.time.ToString("n0"));
        }
    }
}