using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour {

    BoxCollider2D BoxCollider2D;
    Animator Animator;
    public float Speed;
    public float LeftScreenEdge;    // -21.1
    public float RightScreenEdge;   //  21.1

    void Start()
    {
        BoxCollider2D = GetComponent<BoxCollider2D>();
        Animator = GetComponent<Animator>();
	}
	
	void Update()
    {
        Movement();
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

        if (transform.position.x < LeftScreenEdge) { transform.position = new Vector2(LeftScreenEdge, transform.position.y); }
        if (transform.position.x > RightScreenEdge) { transform.position = new Vector2(RightScreenEdge, transform.position.y); }
    }
}