using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Ball : MonoBehaviour {

    SpriteRenderer SpriteRenderer;
    Rigidbody2D Rigidbody2D;
    public float AngleX;
    int BallDir;
    public float Speed;
    public float SpeedCurrent;
    public bool Launched;
    public GameObject Target;
    public bool CheckWhite;
    public bool CheckGreen;
    public bool CheckYellow;
    public bool CheckBlue;
    public bool CheckRed;
    public bool CheckPurple;
    public Sprite WhiteDefault; // #e2e2e2
    public Sprite GreenFruit;   // #00ff01
    public Sprite YellowGrain;  // #ffeb01
    public Sprite BlueDairy;    // #00aaff
    public Sprite RedMeat;      // #ff1500
    public Sprite PurpleBad;    // #aa00ff

    void Start()
    {
        SpriteRenderer = GetComponent<SpriteRenderer>();
        Rigidbody2D = GetComponent<Rigidbody2D>();
        BallDir = Random.Range(0, 2) * 2 - 1;   // Ball Dir can be positive or negative
        Launched = false;
        CheckWhite = true; CheckGreen = false; CheckYellow = false; CheckBlue = false; CheckRed = false; CheckPurple = false;
        SpriteRenderer.sprite = WhiteDefault;
    }

    void Update() 
    {
        SpeedCurrent = Rigidbody2D.velocity.magnitude;
        if (Launched == false)
        {   // Starting y of ball = -9.648623
            transform.position = new Vector2(Target.transform.position.x, Target.transform.position.y + 3.351377f);
        }
        if (Input.GetKey(KeyCode.UpArrow) && Launched == false)
        {
            Launched = true;
            Rigidbody2D.AddForce(new Vector2(AngleX * BallDir, 5) * Speed);  // Gravity RigidBody == 0
        }
        if (Input.GetKey(KeyCode.Q)) { BallWhite(); }
        if (Input.GetKey(KeyCode.W)) { BallGreen(); }
        if (Input.GetKey(KeyCode.E)) { BallYellow(); }
        if (Input.GetKey(KeyCode.Alpha1)) { BallBlue(); }
        if (Input.GetKey(KeyCode.Alpha2)) { BallRed(); }
        if (Input.GetKey(KeyCode.Alpha3)) { BallPurple(); }

        // If Ball is below certain y, check Player's Rigidbody freezeRotation as off.
        if (gameObject.transform.position.y <= -11.63f)
        {
            Target.GetComponent<Rigidbody2D>().freezeRotation = false;
        }
        else
        {
            Target.GetComponent<Rigidbody2D>().freezeRotation = true;
        }
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (CheckWhite == true && collision.gameObject.tag != "wall" && collision.gameObject.tag != "Player" && collision.gameObject.tag != "ground")
        {   // White Ball can destroy all blocks. If destroy a good food block, score down. If destroy bad food block, score up.
            Destroy(collision.gameObject);
        }

        if (collision.gameObject.tag == "fruit" && CheckGreen == true)
        {
            Destroy(collision.gameObject);
        }

        if (collision.gameObject.tag == "grains" && CheckYellow == true)
        {
            Destroy(collision.gameObject);
        }

        if (collision.gameObject.tag == "dairy" && CheckBlue == true)
        {
            Destroy(collision.gameObject);
        }

        if (collision.gameObject.tag == "meat" && CheckRed == true)
        {
            Destroy(collision.gameObject);
        }

        if (collision.gameObject.tag == "bad" && CheckPurple == true)
        {
            Destroy(collision.gameObject);
        }
    }

    public void BallWhite()
    {
        SpriteRenderer.sprite = WhiteDefault;
        CheckWhite = true; CheckGreen = false; CheckYellow = false; CheckBlue = false; CheckRed = false; CheckPurple = false;
    }

    public void BallGreen()
    {
        SpriteRenderer.sprite = GreenFruit;
        CheckWhite = false; CheckGreen = true; CheckYellow = false; CheckBlue = false; CheckRed = false; CheckPurple = false;
    }

    public void BallYellow()
    {
        SpriteRenderer.sprite = YellowGrain;
        CheckWhite = false; CheckGreen = false; CheckYellow = true; CheckBlue = false; CheckRed = false; CheckPurple = false;
    }

    public void BallBlue()
    {
        SpriteRenderer.sprite = BlueDairy;
        CheckWhite = false; CheckGreen = false; CheckYellow = false; CheckBlue = true; CheckRed = false; CheckPurple = false;
    }

    public void BallRed()
    {
        SpriteRenderer.sprite = RedMeat;
        CheckWhite = false; CheckGreen = false; CheckYellow = false; CheckBlue = false; CheckRed = true; CheckPurple = false;
    }

    public void BallPurple()
    {
        SpriteRenderer.sprite = PurpleBad;
        CheckWhite = false; CheckGreen = false; CheckYellow = false; CheckBlue = false; CheckRed = false; CheckPurple = true;
    }
}