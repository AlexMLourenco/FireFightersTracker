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

                <div v-if="notifications[0].state === 'true'">
                  <material-notification
                    class="mb-3"
                    color="warning"
                  >
                    <strong>CO Levels too High - {{notifications[0].value}}</strong> 
                  </material-notification>
                </div>
                <div v-else>
                  <material-notification
                    class="mb-3"
                    color="success"

                  >
                  <strong>Normal CO Levels - {{notifications[0].value}}</strong> 
                  </material-notification>
                </div>
                <div v-if="notifications[1].state === 'true'">
                  <material-notification
                    class="mb-3"
                    color="error"
                  >
                    <strong>Heart Rate too High - {{notifications[1].value}} Bpm</strong> 
                  </material-notification>
                </div>
                <div v-else>
                  <material-notification
                    class="mb-3"
                    color="success"

                  >
                  <strong>Heart Rate normal level - {{notifications[1].value}} Bpm</strong> 
                  </material-notification>
                </div>


              </v-flex>

              <v-flex
                md6
                sm12>
                <h2 class="title font-weight-light">Firefighter A2</h2>

                <div v-if="notifications[2].state === 'true'">
                  <material-notification
                    class="mb-3"
                    color="warning"
                  >
                    <strong>CO Levels too High - {{notifications[2].value}}</strong> 
                  </material-notification>
                </div>
                <div v-else>
                  <material-notification
                    class="mb-3"
                    color="success"

                  >
                  <strong>Normal CO Levels - {{notifications[2].value}}</strong> 
                  </material-notification>
                </div>
                <div v-if="notifications[3].state === 'true'">
                  <material-notification
                    class="mb-3"
                    color="error"
                  >
                    <strong>Heart Rate too High - {{notifications[3].value}} Bpm</strong> 
                  </material-notification>
                </div>
                <div v-else>
                  <material-notification
                    class="mb-3"
                    color="success"

                  >
                  <strong>Heart Rate normal level - {{notifications[3].value}} Bpm</strong> 
                  </material-notification>
                </div>


              </v-flex>
              <v-flex
                md6
                sm12>
                <h2 class="title font-weight-light">Firefighter VR12</h2>
                
                <div v-if="notifications[4].state === 'true'">
                  <material-notification
                    class="mb-3"
                    color="warning"
                  >
                    <strong>CO Levels too High - {{notifications[4].value}} </strong> 
                  </material-notification>
                </div>
                <div v-else>
                  <material-notification
                    class="mb-3"
                    color="success"

                  >
                  <strong>Normal CO Levels - {{notifications[4].value}}</strong> 
                  </material-notification>
                </div>
                <div v-if="notifications[5].state === 'true'">
                  <material-notification
                    class="mb-3"
                    color="error"
                  >
                    <strong>Heart Rate too High - {{notifications[5].value}} Bpm</strong> 
                  </material-notification>
                </div>
                <div v-else>
                  <material-notification
                    class="mb-3"
                    color="success"

                  >
                  <strong>Heart Rate normal level - {{notifications[5].value}} Bpm</strong> 
                  </material-notification>
                </div>
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
      notifications: [{"id":null,"type":"CO","state":"false","date":"1557937500","value":"0","fighter":"a1"}, {"id":null,"type":"HR","state":"false","date":"1557937500","value":"74.36040105","fighter":"a1"}, {"id":null,"type":"CO","state":"true","date":"1557938880","value":"285","fighter":"a2"}, {"id":null,"type":"HR","state":"false","date":"1557938880","value":"94.07684503","fighter":"a2"}, {"id":null,"type":"CO","state":"false","date":"1557936780","value":"0","fighter":"vr12"}, {"id":null,"type":"HR","state":"false","date":"1557936780","value":"82.88614704","fighter":"vr12"}]
    }),

    methods: {
      async fetchAlarms(){
        try{
          const res = await axios.get(this.getUrl() + '/alarms/last')
          this.notifications = res.data
          this.alarms_co = []
          this.alarms_hr = []

          for (var n in this.notifications){
            if (this.notifications.state === "false"){
              this.notifications[n].color == "success"
            }else{
              this.notifications[n].color == "error"
            }
          }
          // console.log(this.notifications)
        }catch(error){
          console.log(error)
        }   
      },
      getUrl(){
        return "http://192.168.160.103:11080"
      }
    },
    created(){
      this.fetchAlarms();
      this.timer = setInterval(this.fetchAlarms, 5000)
    }
  }
</script>
