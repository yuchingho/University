using UnityEngine;

public class OnPlayerRayAttackCollision : MonoBehaviour
{
    private const int PLAYER_DAMAGE = 4; //Damages done by the attack

    private float attackTimer = 0.01f; //Time to make the attack Time dependent instead of physics dependent

    public AudioClip wizardHitAudio;

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "EnemyWizard") //If the object hit is has a special tag
        {
            collision.gameObject.GetComponent<EnemyHealth>().EnemyHurt(PLAYER_DAMAGE); //Takes off the enemy's health
            SoundManager.instance.PlaySingle(wizardHitAudio); //Plays the song informing that the enemy is hit
        }
    }

    private void OnCollisionStay2D(Collision2D collision) 
    {
        if (collision.gameObject.tag == "EnemyWizard") //While the enemy wizard remains in the collision
        {
            if (attackTimer > 0) //If the wizard got damages from this attack before the end of the cooldown, do nothing
                attackTimer -= Time.fixedDeltaTime; 
            else
            {
                attackTimer = 0.01f;
                collision.gameObject.GetComponent<EnemyHealth>().EnemyHurt(PLAYER_DAMAGE); // Takes off enemy's health
            }
        }
    }
}