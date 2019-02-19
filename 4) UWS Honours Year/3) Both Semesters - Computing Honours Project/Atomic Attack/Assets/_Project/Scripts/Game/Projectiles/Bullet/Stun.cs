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
    {
        if (Target.gameObject.name == "E_Swordsman" || Target.gameObject.name ==  "E_Gunman")
        { Target.GetComponent<AI_Human>().Stunned = true; }
        base.HitTarget();
    }
}