<template>
  <v-container
    fluid
    grid-list-xl
    fill-height>
    <v-layout
      justify-center
      align-center
    >
      <v-flex xs12>
        <material-card
          color="#424242">
          <div
            slot="header"
          >
            <div class="title font-weight-light mb-2">
              Alarms             
               <v-icon
                size="20"
              >
                mdi-alarm-light
              </v-icon></div>
            <div class="category">

            </div>
          </div>

          <v-card-text>
            <v-layout
              row
              wrap>
              <v-flex
                md6
                sm12>
                <h2 class="title font-weight-light mb-3">Firefighter A1</h2>
                <material-notification
                  class="mb-3"
                  color="warning"
                >
                  <strong>CO Levels too High - VALUE</strong> 
                </material-notification>

                <material-notification
                  class="mb-3"
                  color="error"
                >
                  <strong>Heart Rate level too high - VALUE </strong>
                </material-notification>


              </v-flex>

              <v-flex
                md6
                sm12>
                <h2 class="title font-weight-light">Firefighter A2</h2>

                <material-notification
                  class="mb-3"
                  color="success"
                >
                  <strong>CO Levels normal - VALUE</strong> 
                </material-notification>

                <material-notification
                  class="mb-3"
                  color="success"
                >
                  <strong>Heart Rate normal - VALUE </strong>
                </material-notification>


              </v-flex>
                            <v-flex
                md6
                sm12>
                <h2 class="title font-weight-light">Firefighter VR12</h2>

                <material-notification
                  class="mb-3"
                  color="warning"
                >
                  <strong>CO Levels too High - VALUE</strong> 
                </material-notification>

                <material-notification
                  class="mb-3"
                  color="error"
                >
                  <strong>Heart Rate level too high - VALUE </strong>
                </material-notification>

              </v-flex>
            </v-layout>
          </v-card-text>
        </material-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import axios from "axios"
  import materialCard from '~/components/material/AppCard'
  import materialNotification from '~/components/material/AppNotification'

  export default {
    layout: 'dashboard',
    components: {
      materialCard,
      materialNotification
    },
    data: () => ({
      color: null,
      colors: [
        'purple',
        'info',
        'success',
        'warning',
        'error'
      ],
      top: true,
      bottom: false,
      left: false,
      right: false,
      snackbar: false,
      notifications: []
    }),

    methods: {
      snack (...args) {
        this.top = false
        this.bottom = false
        this.left = false
        this.right = false

        for (const loc of args) {
          this[loc] = true
        }

        this.color = this.colors[Math.floor(Math.random() * this.colors.length)]

        this.snackbar = true
      },
      async fetchAlarms(){
        try{
          const res = await axios.get(this.getUrl() + '/alarms/all')
          this.notifications = res.data
          this.alarms_co = []
          this.alarms_hr = []
          console.log(this.notifications)
          // for (var n in this.notifications){
          //   if (this.notifications[n].id  ){

          //   }
          // }
          // console.log(this.notifications)
        }catch(error){
          console.log(error)
        }   
      },
      getUrl(){
        return "http://localhost:8080"
      }
    },
    created(){
      this.fetchAlarms();
      this.timer = setInterval(this.fetchAlarms, 5000)
    }
  }
</script>
