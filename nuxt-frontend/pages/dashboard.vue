<template>

  <v-container
    
    fluid
    grid-list-xl
    @load="onDashboardLoad"
  >
    <v-row>
      <v-select
          :items="firefighters_ex"
          filled
          label="Current FireFighter"
          v-model="selectedFirefighter"
          @input="changeFirefighter"

      ></v-select>
      firefighter - {{firefighter}}
    </v-row>

    <v-layout wrap>

      <v-flex
        md12
        sm12
        lg4
      >
        <material-chart-card
          :data="dailySalesChart.data"
          :options="dailySalesChart.options"
          color="info"
          type="Line"
        >
          <h4 class="title font-weight-light">Active Fires</h4>
          <p class="category d-inline-flex font-weight-light">
            <v-icon
              color="green"
              small
            >
              mdi-arrow-up
            </v-icon>
            <span class="green--text">10%</span>&nbsp;
            increase in active fires
          </p>

          <template slot="actions">
            <v-icon
              class="mr-2"
              small
            >
              mdi-clock-outline
            </v-icon>
            <span class="caption grey--text font-weight-light">updated 4 minutes ago</span>
          </template>
        </material-chart-card>
      </v-flex>
      <v-flex
        md12
        sm12
        lg4
      >
        <material-chart-card
          :data="emailsSubscriptionChart.data"
          :options="emailsSubscriptionChart.options"
          :responsive-options="emailsSubscriptionChart.responsiveOptions"
          color="red"
          type="Bar"
        >
          <h4 class="title font-weight-light">Active Firefighters</h4>
          <p class="category d-inline-flex font-weight-light">Number of active firefighters</p>

          <template slot="actions">
            <v-icon
              class="mr-2"
              small
            >
              mdi-clock-outline
            </v-icon>
            <span class="caption grey--text font-weight-light">updated 10 minutes ago</span>
          </template>
        </material-chart-card>
      </v-flex>
      <v-flex
        md12
        sm12
        lg4
      >
        <material-chart-card
          :data="dataCompletedTasksChart.data"
          :options="dataCompletedTasksChart.options"
          color="green"
          type="Line"
        >
          <h3 class="title font-weight-light">Temperature</h3>
          <p class="category d-inline-flex font-weight-light">Graph of temperature by time</p>

          <template slot="actions">
            <v-icon
              class="mr-2"
              small
            >
              mdi-clock-outline
            </v-icon>
            <span class="caption grey--text font-weight-light">campaign sent 26 minutes ago</span>
          </template>
        </material-chart-card>
      </v-flex>
      <v-flex
        sm6
        xs12
        md6
        lg3
      >
        <material-stats-card
          color="green"
          icon="mdi-chemical-weapon"
          title="CO"
          :value="firefighter.env.co"
          sub-icon="mdi-update"
          sub-text="5 seconds ago"
        />
      </v-flex>
      <v-flex
        sm6
        xs12
        md6
        lg3
      >
                <!-- :value="firefighter.env.temp" -->

        <material-stats-card
          color="orange"
          icon="mdi-temperature-celsius"
          title="Temperature"
          small-value="C"
          :value="firefighter.env.temp"
          sub-icon="mdi-update"
          sub-icon-color="error"
          sub-text="5 seconds ago"
          sub-text-color="text-primary"
        />
        
      </v-flex>
      <v-flex
        sm6
        xs12
        md6
        lg3
      >
        <material-stats-card
          color="red"
          icon="mdi-heart-pulse"
          title="Heart Rate"
          :value="firefighter.hr.hr"
          small-value="bpm"
          sub-icon="mdi-update"
          sub-text="5 seconds ago"
        />
      </v-flex>
      <v-flex
        sm6
        xs12
        md6
        lg3
      >
        <material-stats-card
          color="info"
          icon="mdi-water-percent"
          title="Humidity"
          :value="firefighter.env.hum"
          small-value="%"
          sub-icon="mdi-update"
          sub-text="5 seconds ago"
        />
      </v-flex>
      <!-- <v-flex
        sm6
        xs12
        md6
        lg3
      >
        <material-stats-card
          color="info"
          icon="mdi-twitter"
          title="Followers"
          value="+245"
          sub-icon="mdi-update"
          sub-text="Just Updated"
        />
      </v-flex>
            <v-flex
        sm6
        xs12
        md6
        lg3
      >
        <material-stats-card
          color="info"
          icon="mdi-twitter"
          title="Followers"
          value="+245"
          sub-icon="mdi-update"
          sub-text="Just Updated"
        />
      </v-flex>
            <v-flex
        sm6
        xs12
        md6
        lg3
      >
        <material-stats-card
          color="info"
          icon="mdi-twitter"
          title="Followers"
          value="+245"
          sub-icon="mdi-update"
          sub-text="Just Updated"
        />
      </v-flex>
            <v-flex
        sm6
        xs12
        md6
        lg3
      >
        <material-stats-card
          color="info"
          icon="mdi-twitter"
          title="Followers"
          value="+245"
          sub-icon="mdi-update"
          sub-text="Just Updated"
        />
      </v-flex> -->
    </v-layout>
  </v-container>
</template>

<script>
  import axios from "axios"
  import materialCard from '~/components/material/AppCard'
  import materialChartCard from '~/components/material/AppChartCard'
  import materialStatsCard from '~/components/material/AppStatsCard'

  export default {
    layout: 'dashboard',
    middleware: 'authentication',
    components: {
      materialCard,
      materialChartCard,
      materialStatsCard
    },
    data() {
      return {
        dailySalesChart: {
          data: {
            labels: ['M', 'T', 'W', 'T', 'F', 'S', 'S'],
            series: [
              [12, 17, 7, 17, 23, 18, 38]
            ]
          },
          options: {
            low: 0,
            high: 50, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
            chartPadding: {
              top: 0,
              right: 0,
              bottom: 0,
              left: 0
            }
          }
        },
        dataCompletedTasksChart: {
          data: {
            labels: ['12am', '3pm', '6pm', '9pm', '12pm', '3am', '6am', '9am'],
            series: [
              [23, 75, 45, 30, 28, 24, 20, 19]
            ]
          },
          options: {
            low: 0,
            high: 100, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
            chartPadding: {
              top: 0,
              right: 0,
              bottom: 0,
              left: 0
            }
          }
        },
        emailsSubscriptionChart: {
          data: {
            labels: ['Ja', 'Fe', 'Ma', 'Ap', 'Mai', 'Ju', 'Jul', 'Au', 'Se', 'Oc', 'No', 'De'],
            series: [
              [542, 443, 320, 780, 553, 453, 326, 434, 568, 610, 756, 895]
            ]
          },
          options: {
            axisX: {
              showGrid: false
            },
            low: 0,
            high: 1000,
            chartPadding: {
              top: 0,
              right: 5,
              bottom: 0,
              left: 0
            }
          },
          responsiveOptions: [
            ['screen and (max-width: 640px)', {
              seriesBarDistance: 5,
              axisX: {
                labelInterpolationFnc: function (value) {
                  return value[0]
                }
              }
            }]
          ]
        },
        headers: [
          {
            sortable: false,
            text: 'ID',
            value: 'id'
          },
          {
            sortable: false,
            text: 'Name',
            value: 'name'
          },
          {
            sortable: false,
            text: 'Salary',
            value: 'salary',
            align: 'right'
          },
          {
            sortable: false,
            text: 'Country',
            value: 'country',
            align: 'right'
          },
          {
            sortable: false,
            text: 'City',
            value: 'city',
            align: 'right'
          }
        ],
        items: [
          {
            name: 'Dakota Rice',
            country: 'Niger',
            city: 'Oud-Tunrhout',
            salary: '$35,738'
          },
          {
            name: 'Minerva Hooper',
            country: 'Curaçao',
            city: 'Sinaai-Waas',
            salary: '$23,738'
          }, {
            name: 'Sage Rodriguez',
            country: 'Netherlands',
            city: 'Overland Park',
            salary: '$56,142'
          }, {
            name: 'Philip Chanley',
            country: 'Korea, South',
            city: 'Gloucester',
            salary: '$38,735'
          }, {
            name: 'Doris Greene',
            country: 'Malawi',
            city: 'Feldkirchen in Kārnten',
            salary: '$63,542'
          }
        ],
        tabs: 0,
        list: {
          0: false,
          1: false,
          2: false
        },
        firefighters_ex: [
          'vr12', 'a1', 'a2'
        ],
        selectedFirefighter: 'vr12',
        loading:false,
        firefighter:{ "id": "vr12", "gps": { "id": 51438, "date": "1557944700,0", "name": "vr12", "type": "gps", "gps_alt_tag": "1110", "gps_tag_lat": "40.06451668", "gps_tag_long": "-8.1615259", "gps_time_tag": "1110", "coordinates": [ "-8.1615259", "40.06451668" ] }, "env": { "id": 51444, "date": "1557944700,0", "name": "vr12", "type": "env", "co": "0", "temp": "28", "hgt": "-22", "no2": "0.9", "hum": "-1", "lum": "24", "battery": "101" }, "hr": { "id": 51493, "date": "1557944700,0", "name": "vr12", "type": "hr", "hr": "101.0217575" } }

      }
    },
    methods: {
      complete (index) {
        this.list[index] = !this.list[index]
      },
      async onDashboardLoad(){
        const firefighters = await this.getFirefighters();
        this.firefighters = firefighters;
        console.log('firefighters dashboard load', this.firefighters)
        for (var f in this.firefighters){
          console.log(this.firefighters[f].id === 'vr12')
          console.log(this.firefighters[f])
          if (this.firefighters[f].id === 'vr12'){
            this.firefighter = this.firefighters[f]
            console.log('firefighter == vr12', this.firefighters[f])
          }
        }
      },
      async getFirefighters(){
        // console.log($this.store.actions.get_firefighters)
        // const res = await this.$store.state.dispatch("get_firefighters")
        try{
          const res = await axios.get(this.getUrl() + '/fighters/all')
          return res.data
        }catch(error){
          console.log(error)
        }
      }
      ,
      async fetchAllInfo(){
        try{
          const res_dash = await axios.get(this.getUrl() + '/dashboard')
          const res_fighters = await axios.get(this.getUrl() + '/fighters/all')
          this.firefighters = res_fighters.data
          //Separa os bombeiros 
          this.firefighters.forEach( firefighter => firefighter.gps.coordinates = [firefighter.gps.gps_tag_long,firefighter.gps.gps_tag_lat])
          console.log(this.firefighters)
          for (var f in this.firefighters){
            if (this.firefighters[f].id === this.selectedFirefighter){
              this.firefighter = this.firefighters[f]
            }
          }

          //this.dataCompletedTasksChart.series = 
          
          this.info = res_dash.data
          console.log(this.info)

        }catch(error){
          console.log(error)
        }   
      },
      changeFirefighter() {
        console.log('mudei estado')
        console.log(this.selectedFirefighter)
        // this.$emit('changeFirefighter', this.message)
        for (var f in this.firefighters){
            if (this.firefighters[f].id === this.selectedFirefighter){
              this.firefighter = this.firefighters[f]
            }
        }
        console.log(this.firefighter)
        

      },
      getUrl(){
        return "http://localhost:8080"
      }
    },
    mounted() {
      this.$nextTick(() => {
        // this.$nuxt.$loading.start()

        // setTimeout(() => this.$nuxt.$loading.finish(), 500)
        this.onDashboardLoad()
        console.log('onload firefighter', this.firefighter)
        /*this.dailySalesChart.options = {
          lineSmooth: this.$chartist.Interpolation.cardinal({
            tension: 0
          }),
          low: 0,
          high: 50, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
          chartPadding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
          }
        };
        this.dataCompletedTasksChart.options = {
          lineSmooth: this.$chartist.Interpolation.cardinal({
            tension: 0
          }),
          low: 0,
          high: 1000, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
          chartPadding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
          }
        };*/
      });
    },
    created(){
      console.log('entrei aqui')
      this.fetchAllInfo()
      this.timer = setInterval(this.fetchAllInfo, 5000)
    }
  }
</script>

<style scoped>
  /* .v-container {
    padding-top:10px;
  }
  .v-select {
    position: absolute;
    left: 10px;
    top: 0px;

  } */
</style>