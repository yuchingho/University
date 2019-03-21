using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F15_Phosphorus : MonoBehaviour {

    [Space(-10), Header("[ Mist: F15_Phosphorus ]")]
    #pragma warning disable
    [SerializeField] string Effect = "Burns everyone.";

    void OnTriggerStay2D(Collider2D collision)
    {
        HealthSystem HP = collision.gameObject.GetComponent<HealthSystem>();
        if (collision.gameObject.name == "Castle Health")
        { /* Nothing. */ }
        else if (collision.gameObject.tag == "Enemy")
        { HP.DamageTaken(0.7f); collision.gameObject.GetComponent<AI_Human>().Burned = true;
            if (collision.gameObject.GetComponent<AI_Human>().MovementSpeed != 0)
            {   collision.gameObject.GetComponent<AI_Human>().MovementSpeed = 2f; } } } }