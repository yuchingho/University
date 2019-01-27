using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class E_Gunman : AI_Enemy {
    // Child Class E_Gunman inheriting from Enemy.
    [Space(10), Header("[^ Child: Enemy ]")]
    [Space(10), Header("[^ Child: Gunman ] Attack")]
    [SerializeField] GameObject Projectile;
    [SerializeField] bool Stunned;
    [SerializeField] bool Blinded;
    [SerializeField] Transform FireLocation;
    [SerializeField] bool OnCastle;

    void Reset()
    {   // Health = 6;
        CostValue = 0;
        ScoreValue = 2;
        MovementSpeed = 1f;
        RunAwayTimer = 3f;
        AttackDamage = 3;
        LookRadius = 4f;
        AttackRadius = 4f;
        AttackRate = 0.75f;
    }

    protected override void Update()
    {
        base.Update();  // If not on Castle, run as normal. If so, range = 10f.
        if (OnCastle == true)
        {
            MovementSpeed = 0f;
            LookRadius = 10f;
            AttackRadius = 10f;
        }
    }

    protected override void PlayAnimationAttack()
    {
        if (Time.time > NextAttackTime)
        {
            base.PlayAnimationAttack();
            Shoot();
        }
    }

    protected virtual void Shoot()
    {   // Instantiating Bullet prefab.
        GameObject Bullet = Instantiate(Projectile, FireLocation.position, FireLocation.rotation);
        Bullet bullet = Bullet.GetComponent<Bullet>();
        // If eBullet != null, use E_Bullet's script Seek method.
        if (bullet != null) { bullet.Seek(Target); }
        // Add Bullet's Damage on the script prefab. 
        // AttackDamage value here is just reference.
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