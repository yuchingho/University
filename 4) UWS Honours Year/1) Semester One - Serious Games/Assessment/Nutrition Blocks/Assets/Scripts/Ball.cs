using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Ball : MonoBehaviour {

    ManagerGame ManagerGame;    // Getting the ManagerGame Script.
    Rigidbody2D Rigidbody2D;
    CircleCollider2D CircleCollider2D;
    SpriteRenderer SpriteRenderer;
    AudioSource AudioSource;
    GameObject PlayerObject;
    int Direction;
    public PhysicsMaterial2D PhysicsMaterial;
    public float AngleX;
    public bool CheckDefault;
    public bool CheckFruit;
    public bool CheckGrains;
    public bool CheckDairy;
    public bool CheckMeat;
    public bool CheckBad;
    public Sprite WhiteDefault; // #e2e2e2.
    public Sprite GreenFruit;   // #00ff01.
    public Sprite YellowGrains; // #ffeb01.
    public Sprite BlueDairy;    // #00aaff.
    public Sprite RedMeat;      // #ff1500.
    public Sprite PurpleBad;    // #aa00ff.
    public AudioClip BlockHit;
    
    void Start()
    {
        ManagerGame = GameObject.Find("ManagerGame").GetComponent<ManagerGame>();
        SpriteRenderer = GetComponent<SpriteRenderer>();
        Rigidbody2D = GetComponent<Rigidbody2D>();
        CircleCollider2D = GetComponent<CircleCollider2D>();
        AudioSource = GetComponent<AudioSource>();
        PlayerObject = GameObject.Find("Player"); // For the Ball to be ontop of Player before launching.
        Direction = Random.Range(0, 2) * 2 - 1;   // Ball Launch Direction can be left(1) or right(-1).
        SpriteRenderer.sprite = WhiteDefault;
        BallWhite();
    }

    void Update()
    {
        ManagerGame.BallSpeedCurrent = Rigidbody2D.velocity.magnitude;  // Displaying speed of ball in Inspector.
        if (ManagerGame.BallLaunched == false)
        {   // Starting Ball-Y = -9.648623, so Ball is ontop of Player.
            transform.position = new Vector2(PlayerObject.transform.position.x, PlayerObject.transform.position.y + 3.351377f);
        }
        if (ManagerGame.BallLaunched == false && Input.GetKey(KeyCode.Space))
        {   // Launch Ball to start Score Timer.
            ManagerGame.BallLaunched = true;                    // Gravity of RigidBody == 0.
            Rigidbody2D.AddForce(new Vector2(AngleX * Direction, 5) * ManagerGame.BallSpeed);
        }
        if (Input.GetKey(KeyCode.A) && ManagerGame.PauseActive == false) { BallWhite(); }
        if (Input.GetKey(KeyCode.S) && ManagerGame.PauseActive == false) { BallGreen(); }
        if (Input.GetKey(KeyCode.D) && ManagerGame.PauseActive == false) { BallYellow(); }
        if (Input.GetKey(KeyCode.Q) && ManagerGame.PauseActive == false) { BallBlue(); }
        if (Input.GetKey(KeyCode.W) && ManagerGame.PauseActive == false) { BallRed(); }
        if (Input.GetKey(KeyCode.E) && ManagerGame.PauseActive == false) { BallPurple(); }
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        AudioSource.PlayOneShot(BlockHit, 0.2f);
        if (ManagerGame.PlayerIsPlaying == true)
        {
            Rigidbody2D.drag = 0;
            Rigidbody2D.gravityScale = 0;
            CircleCollider2D.sharedMaterial = PhysicsMaterial;
            int newDir = Random.Range(0, 2) * 2 - 1; // In respawn, Ball direction can be new left or right.
            Direction = newDir;
        }
        else
        {   // Reducing bounce when Ball hits Player.
            Rigidbody2D.drag = 1;
            Rigidbody2D.gravityScale = 2;
            if (collision.gameObject.tag == "wall") { CircleCollider2D.sharedMaterial = null; }
        }
    }

    // Boolean values checklist.
    public void BallWhite()
    {
        SpriteRenderer.sprite = WhiteDefault;
        CheckDefault = true;
        CheckFruit = false;
        CheckGrains = false;
        CheckDairy = false;
        CheckMeat = false;
        CheckBad = false;
    }
    public void BallGreen()
    {
        SpriteRenderer.sprite = GreenFruit;
        CheckDefault = false;
        CheckFruit = true;
        CheckGrains = false;
        CheckDairy = false;
        CheckMeat = false;
        CheckBad = false;
    }
    public void BallYellow()
    {
        SpriteRenderer.sprite = YellowGrains;
        CheckDefault = false;
        CheckFruit = false;
        CheckGrains = true;
        CheckDairy = false;
        CheckMeat = false;
        CheckBad = false;
    }
    public void BallBlue()
    {
        SpriteRenderer.sprite = BlueDairy;
        CheckDefault = false;
        CheckFruit = false;
        CheckGrains = false;
        CheckDairy = true;
        CheckMeat = false;
        CheckBad = false;
    }
    public void BallRed()
    {
        SpriteRenderer.sprite = RedMeat;
        CheckDefault = false;
        CheckFruit = false;
        CheckGrains = false;
        CheckDairy = false;
        CheckMeat = true;
        CheckBad = false;
    }
    public void BallPurple()
    {
        SpriteRenderer.sprite = PurpleBad;
        CheckDefault = false;
        CheckFruit = false;
        CheckGrains = false;
        CheckDairy = false;
        CheckMeat = false;
        CheckBad = true;
    }
}