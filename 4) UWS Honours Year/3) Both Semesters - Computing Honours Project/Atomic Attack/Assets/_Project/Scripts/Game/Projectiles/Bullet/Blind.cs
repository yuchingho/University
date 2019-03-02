using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Blind : _Bullet {

    void Reset()
    {
        Damage = 2;
        Speed = 5;
    }

    protected override void HitTarget()
    {   // So only Units will be Blinded, and not the Castle.
        if (Target.gameObject.tag == "Enemy")
        { Target.GetComponent<AI_Human>().Blinded = true; }
        base.HitTarget();
    }
}