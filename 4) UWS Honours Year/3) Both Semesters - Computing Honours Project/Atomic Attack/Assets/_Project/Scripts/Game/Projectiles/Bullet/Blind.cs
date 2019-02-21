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
    {   // If the Bullet's Target's target is Blinded, then just destroy.
        if (Target.gameObject.name == "E_Swordsman" || Target.gameObject.name == "E_Gunman")
        { Target.GetComponent<AI_Human>().Blinded = true; }
        base.HitTarget();
    }
}