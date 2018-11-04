using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour {

    Animator Animator;
    public bool Control;
    public float Speed;

    void Start()
    {
        Animator = GetComponent<Animator>();
        Control = true;
    }

    void Update()
    {
        if (Control == true) { Movement(); }

        if (gameObject.transform.position.y >= -12.5)
        {
            Control = false;
            Animator.Play("Idle");
        }
    }

    void Movement()
    {
        if (Input.GetKey(KeyCode.LeftArrow))
        {   // localScale: x == left/right | y == up/down
            Animator.Play("Running");
            transform.localScale = new Vector2(-1, 1);
            transform.position += Vector3.left * Time.deltaTime * Speed;
        }
        else if (Input.GetKey(KeyCode.RightArrow))
        {
            Animator.Play("Running");
            transform.localScale = new Vector2(1, 1);
            transform.position += Vector3.right * Time.deltaTime * Speed;
        }
        else
        {
            Animator.Play("Idle");
        }
    }
}