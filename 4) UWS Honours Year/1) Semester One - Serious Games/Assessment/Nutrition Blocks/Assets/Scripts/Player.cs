using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour {

    ManagerGame ManagerGame;    // Getting the ManagerGame Script.
    Rigidbody2D Rigidbody2D;
    Animator Animator;
    SpriteRenderer SpriteRenderer;

    void Start()
    {
        ManagerGame = GameObject.Find("ManagerGame").GetComponent<ManagerGame>();
        Rigidbody2D = GetComponent<Rigidbody2D>();
        Animator = GetComponent<Animator>();
        SpriteRenderer = GetComponent<SpriteRenderer>();
    }

    void Update()
    {
        ManagerGame.PlayerSpeedCurrent = Rigidbody2D.velocity.magnitude;
        if (ManagerGame.PlayerIsPlaying == true) { Movement(); }
        else
        {   // If Player hit by Ball, Player stops moving.
            Rigidbody2D.freezeRotation = false;
            Animator.Play("Idle");
            SpriteRenderer.color = Color.red;
        }
    }

    void Movement()
    {
        if (Input.GetKey(KeyCode.LeftArrow))
        {   // LocalScale: x == left/right | y == up/down.
            Animator.Play("Running");
            transform.localScale = new Vector2(-1, 1);
            transform.position += Vector3.left * Time.deltaTime * ManagerGame.PlayerSpeed;
        }
        else if (Input.GetKey(KeyCode.RightArrow))
        {
            Animator.Play("Running");
            transform.localScale = new Vector2(1, 1);
            transform.position += Vector3.right * Time.deltaTime * ManagerGame.PlayerSpeed;
        }
        else { Animator.Play("Idle"); }
    }

    void OnTriggerEnter2D(Collider2D collision)
    {   // If Player hit by Ball, Player stops moving.
        if (collision.gameObject.name == "Ball") { ManagerGame.PlayerIsPlaying = false; }
    }
}