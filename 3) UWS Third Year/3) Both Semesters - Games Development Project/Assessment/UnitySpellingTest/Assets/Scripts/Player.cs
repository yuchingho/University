using System.Collections.Generic;
using System.Linq;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Player : MonoBehaviour
{
    private float speed = 5f; //Manages the speed of the player's movements

    public float leftEdge; //Defines the left limit of the map the player cannot cross
    public float rightEdge; //Defines the right limit of the map the player cannot cross
    public float bottomEdge; //Defines the top limit of the map the player cannot cross
    public float topEdge; //Defines the left bottom of the map the player cannot cross

    public bool enableMovements; //Enables the player's movements or not depending of the scene
    public bool enableShoot; //Enables the player to attack or not depending of the scene

    // Player Health
    [SerializeField] public int MaxHealth;
    [SerializeField] public int CurrentHealth;
    [SerializeField] public int Lives;
    [SerializeField] private Transform RespawnPoint;
    public GameObject StopScript;

    private Animator animator; //The animator object permitting to change the player's animations
    private Animator xSpellAnimator; //The animator object permitting to change the casting animation of the spray attack
    private Animator cSpellAnimator; //The animator object permitting to change the casting animation of the ray attack

    public GameObject attackParticlePrefab; //Attack particle object

    public int authorizedAttacks; //Permits to manage which attack is available or not

    private float uniqueFireAttackTimer;
    private float uniqueFireAttackCd = 1f; //Cooldown of the spray attack

    public GameObject uniqueWaterAttackMarkPrefab; //Ray mark object (before it makes damages)
    public GameObject uniqueWaterAttackParticlePrefab; //Ray attack object

    private float uniqueWaterAttackTimer = 0;
    private float uniqueWaterAttackCd = 2f; //Cooldown of the water attack

    private float uniqueWaterAttackParticleTimer = 0.3f;
    private float uniqueWaterAttackParticleCd = 0.3f; //Length of the ray attack

    private int uniqueWaterAttackCount = 0;
    private float attackAngle; //Angle calculated to hit the enemy wizard

    private List<KeyValuePair<GameObject, Vector2>> attackParticlesList; //Manages the particles being thrown to make them move

    public string Dead;

    //Audio part
    public AudioClip basicAttackAudio;
    public AudioClip rayAttackAudio;
    public AudioClip playerDeadAudio;

    void Start()
    {
        CurrentHealth = MaxHealth;

        attackParticlesList = new List<KeyValuePair<GameObject, Vector2>>();

        animator = gameObject.GetComponent<Animator>();

        if (authorizedAttacks > 0)
            xSpellAnimator = GameObject.Find("XAnimation").GetComponent<Animator>();

        if (authorizedAttacks > 1)
            cSpellAnimator = GameObject.Find("CAnimation").GetComponent<Animator>();
    }

    void Update()
    {
        attackParticlesList = attackParticlesList.Where(kvp => kvp.Key != null).ToList(); //Deletes every destroyed particle from the list

        foreach (KeyValuePair<GameObject, Vector2> kvp in attackParticlesList) //Calculates where the player's particles must be in the map
            kvp.Key.transform.Translate(kvp.Value * Time.deltaTime * 5f);

        if (enableMovements)
            Movement(); //Permits the player to move

        if (enableShoot) 
        {
            if (Input.GetKeyDown(KeyCode.Z))
                Attack(); //Basic attack
            else if (animator.GetBool("Attack") == true) //Changes the player's animations
                animator.SetBool("Attack", false);

            if (authorizedAttacks > 0) //Permits to use the fire attack
            {
                if (uniqueFireAttackTimer > 0) //Disables the player to use the fire attack if the cooldown isn't over
                    uniqueFireAttackTimer -= Time.deltaTime;
                else //Enables the player to use the fire attack if the cooldown is over
                {
                    if (!xSpellAnimator.GetBool("IsReady")) //Changes the animation of the cooldown
                        xSpellAnimator.SetBool("IsReady", true);
                    if (Input.GetKeyDown(KeyCode.X))
                        FireAttack();
                }

                if (authorizedAttacks > 1) //Permits to use the water attack
                {
                    if (uniqueWaterAttackTimer > 0) //Disables the player to use the water attack if the cooldown isn't over
                        uniqueWaterAttackTimer -= Time.deltaTime;
                    else //Enables the player to use the fire attack if the cooldown is over
                    {
                        if (!cSpellAnimator.GetBool("IsReady")) //Changes the animation of the cooldown
                            cSpellAnimator.SetBool("IsReady", true);
                        if (Input.GetKeyDown(KeyCode.C))
                            WaterAttack();
                    }

                }
            }
        }

        if (uniqueWaterAttackCount > 0) // If the water attack is being thrown, the player can't move and attack
            WaterAttack();
    }

    void Attack() //Basick attack
    {
        animator.SetBool("Attack", true);

        SoundManager.instance.PlaySingle(basicAttackAudio); //Plays the sound of the shoot

        Vector2 particleVector = new Vector2(0, 1.5f);

        attackParticlesList.Add(CreateAttackParticle(2f, particleVector));
    }

    void FireAttack()
    {
        animator.SetBool("Attack", true);
        xSpellAnimator.SetBool("IsReady", false);

        SoundManager.instance.PlaySingle(basicAttackAudio); //Plays the sound of the shoot

        uniqueFireAttackTimer = uniqueFireAttackCd;

        for (float f = -0.6f; f <= 0.6f; f += 0.2f)
        {
            Vector2 particleVector = new Vector2(f, 1.5f);

            attackParticlesList.Add(CreateAttackParticle(4f, particleVector));
        }
    }

    public KeyValuePair<GameObject, Vector2> CreateAttackParticle(float lifeSpan, Vector2 particleVector)
    {
        GameObject attackParticle = Instantiate(attackParticlePrefab, new Vector2(transform.position.x, transform.position.y), new Quaternion());
        Destroy(attackParticle, lifeSpan);

        return new KeyValuePair<GameObject, Vector2>(attackParticle, particleVector);
    }

    void WaterAttack()
    {
        if (uniqueWaterAttackCount == 0) //If the water attack is in the first phase (casting)
        {
            animator.SetBool("Attack", true);
            cSpellAnimator.SetBool("IsReady", false);

            enableMovements = false; //Disables the player's movements
            enableShoot = false; //Disables the player's attacks

            uniqueWaterAttackTimer = uniqueWaterAttackCd; //Sets the cooldown of the cast
            uniqueWaterAttackCount = 1;

            GameObject attackParticle = Instantiate(uniqueWaterAttackMarkPrefab, new Vector2(transform.position.x, transform.position.y + 0.15f), new Quaternion());

            //Calculates the attack angle between the enemy wizard and the player
            Vector3 enemyPosition = GameObject.FindGameObjectWithTag("EnemyWizard").transform.position;

            float opposite = Mathf.Abs(enemyPosition.y - transform.position.y);
            float adjacent = Mathf.Abs(enemyPosition.x - transform.position.x);

            attackAngle = Mathf.Atan2(opposite, adjacent) * Mathf.Rad2Deg; //Trigonometry formula

            if (transform.position.x > enemyPosition.x)
                attackAngle = -attackAngle + 180;

            attackParticle.transform.Rotate(new Vector3(0, 0, attackAngle + 90));
            Destroy(attackParticle, 0.3f);
        }
        else //If the water attack is in the second phase (attack)
        {
            if (uniqueWaterAttackParticleTimer > 0) //If the cast is not over
                uniqueWaterAttackParticleTimer -= Time.deltaTime;
            else //If the cast is over
            {
                animator.SetBool("Attack", true);

                SoundManager.instance.PlaySingle(rayAttackAudio);

                GameObject attackParticle = Instantiate(uniqueWaterAttackParticlePrefab, new Vector2(transform.position.x, transform.position.y + 0.15f), new Quaternion());

                attackParticle.transform.Rotate(new Vector3(0, 0, attackAngle + 90));
                Destroy(attackParticle, 0.4f);

                enableMovements = true; //Re enables the player's movements
                enableShoot = true; //Re enables the player's attacks

                uniqueWaterAttackParticleTimer = uniqueWaterAttackParticleCd; //Sets the cooldown of the attack
                uniqueWaterAttackCount = 0;
            }
        }
    }

    void Movement()
    {
        //Get the touch pressed
        float axisX = Input.GetAxis("Horizontal");
        float axisY = Input.GetAxis("Vertical");

        transform.Translate(new Vector2(axisX, axisY) * Time.deltaTime * speed);

        animator.SetFloat("HorizontalSpeed", axisX);
        animator.SetFloat("VerticalSpeed", axisY);

        //Blocks movement at the edges of the screen
        var pos = Camera.main.WorldToViewportPoint(transform.position);
        pos.x = Mathf.Clamp(pos.x, leftEdge, rightEdge);
        pos.y = Mathf.Clamp(pos.y, bottomEdge, topEdge);
        transform.position = Camera.main.ViewportToWorldPoint(pos);
    }

    public void PlayerHurt(int Damage)
    {
        CurrentHealth -= Damage;

        if (CurrentHealth <= 0)
        {
            Lives--;
            transform.position = RespawnPoint.transform.position;
            CurrentHealth = MaxHealth;
        }

        if (Lives <= 0)
        {
            SoundManager.instance.PlayDeadAudio(playerDeadAudio);
            // Load Death Scene
            SceneManager.LoadScene(Dead);
        }
    }

    void SetMaxHealth()
    {
        CurrentHealth = MaxHealth;
    }
}