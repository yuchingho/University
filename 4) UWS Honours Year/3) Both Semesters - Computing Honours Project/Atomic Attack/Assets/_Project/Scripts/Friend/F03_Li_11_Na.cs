using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F03_Li_11_Na : F_Gunman {

    // Child Class F03_Li_11_Na inheriting from F_Gunman.
    [Space( 10), Header("[^ Child: F03_Lithium ]")]
    [Space(-10), Header("[^ Child: F11_Sodium ]")]
    #pragma warning disable
    [SerializeField] string Effect = "Stun";

    // if stunned, animation.enabled=false;
}