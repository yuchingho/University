using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F07_Nitrogen : MonoBehaviour {

    [Space(-10), Header("[ Mist: F07_Nitrogen ]")]
    #pragma warning disable
    [SerializeField] string Effect = "Freezes everyone.";

    void OnTriggerStay2D(Collider2D collision)
    {

        if (collision.gameObject.name == "Castle Health")
        { /* Nothing. */}
        else if (collision.gameObject.tag == "Enemy" || collision.gameObject.tag == "Friend")
        {
            if (collision.gameObject.GetComponent<AI_Human>().MovementSpeed != 0)
            { collision.gameObject.GetComponent<AI_Human>().MovementSpeed = 0.5f; } } } }