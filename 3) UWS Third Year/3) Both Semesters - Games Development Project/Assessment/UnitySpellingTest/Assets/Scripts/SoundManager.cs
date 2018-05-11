using UnityEngine;

public class SoundManager : MonoBehaviour
{
    public AudioSource musicSource;

    public AudioSource efxSource;
    public static SoundManager instance = null;

    void Awake()
    {
        if (instance == null)
            instance = this;

        DontDestroyOnLoad(gameObject);
    }

    private void Start()
    {
        musicSource.Play();
    }

    public void PlaySingle(AudioClip clip)
    {
        efxSource.PlayOneShot(clip);
    }

    public void PlayDeadAudio(AudioClip clip)
    {
        efxSource.clip = clip;
        efxSource.Play();
    }
}