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
    [SerializeField] bool OnCastle;

    void Reset()
    {
        CostValue = 0;
        ScoreValue = 2000;
        MovementSpeed = 1f;
        RunAwayTimer = 3f;
        AttackDamage = 1;
        LookRadius = 3.5f;
        AttackRadius = 3.5f;
        AttackRate = 0.75f;
    }

    protected override void Update()
    {
        base.Update();  // If not on Castle, run as normal. If so, range = 10f.
        if (OnCastle == true) { MovementSpeed = 0f; LookRadius = 10f; AttackRadius = 10f; }
    }

    protected override void LookAtTarget()
    {
        if (OnCastle == false) { base.LookAtTarget(); }
        else
        {   // When on Castle, different Angle algorithm so will face Target correctly.
            Vector3 dir = Target.position - transform.position;
            float Angle = Mathf.Atan2(dir.z, dir.x) * Mathf.Rad2Deg;
            if (Angle <= 160) { MovementDirection = -1; }
            if (Angle >= 170) { MovementDirection =  1; Angle -= 180; }
            transform.rotation = Quaternion.AngleAxis(Angle, Vector3.left);
        }
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Ground") { Grounded = true; }
        //Destroy(GetComponent<SpawnOnCastle>());
        else if (collision.gameObject.tag == "Castle") { OnCastle = true; }
    }
}