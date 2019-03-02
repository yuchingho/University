using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Stun : _Bullet {

    void Reset()
    {
        Damage = 2;
        Speed = 5;
    }

    protected override void HitTarget()
    {   // So only Units will be Stunned, and not the Castle.
        if (Target.gameObject.tag == "Enemy")
        { Target.GetComponent<AI_Human>().Stunned = true; }
        base.HitTarget();
    }
}