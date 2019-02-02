using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F03_Lithium : F_Gunman {

    // Child Class F03_Lithium inheriting from F_Gunman.
    [Space(10), Header("[^ Child: F03_Lithium ] Damage")]
    #pragma warning disable
    [SerializeField] string Effect = "Stun";

    // if stunned, animation.enabled=false;
}