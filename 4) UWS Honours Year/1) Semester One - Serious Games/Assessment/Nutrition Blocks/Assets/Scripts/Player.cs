using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour {

    Rigidbody2D Rigidbody2D;
    BoxCollider2D BoxCollider2D;
    Animator Animator;
    public float Speed;

	void Start()
    {
        Rigidbody2D = GetComponent<Rigidbody2D>();
        BoxCollider2D = GetComponent<BoxCollider2D>();
        Animator = GetComponent<Animator>();
	}
	
	void Update()
    {
        Movement();
    }

    void Movement()
    {   // To not flip character, Rigidbody2D Contraints freeze Z
        if (Input.GetKey(KeyCode.LeftArrow))
        {
            Animator.Play("Running");
            transform.localScale = new Vector2(-1, 1);  // x == left/right | y == up/down
            transform.position += Vector3.left * Time.deltaTime * Speed;
        }
        
        if (Input.GetKeyUp(KeyCode.LeftArrow))
        {
            Animator.Play("Idle");
        }

        if (Input.GetKey(KeyCode.RightArrow))
        {
            Animator.Play("Running");
            transform.localScale = new Vector2(1, 1);
            transform.position += Vector3.right * Time.deltaTime * Speed;
        }

        if (Input.GetKeyUp(KeyCode.RightArrow))
        {
            Animator.Play("Idle");
        }
    }
}
